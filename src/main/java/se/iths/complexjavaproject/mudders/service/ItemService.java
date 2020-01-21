package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.complexjavaproject.mudders.entity.Item;
import se.iths.complexjavaproject.mudders.entity.ItemAmount;
import se.iths.complexjavaproject.mudders.entity.ItemAmountId;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.*;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;
import se.iths.complexjavaproject.mudders.model.ItemModel;
import se.iths.complexjavaproject.mudders.repository.ItemAmountRepository;
import se.iths.complexjavaproject.mudders.repository.ItemRepository;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;

import java.util.Optional;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemAmountRepository itemAmountRepository;

    private final PlayerCharacterRepository playerCharacterRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                       ItemAmountRepository itemAmountRepository,
                       PlayerCharacterRepository playerCharacterRepository) {

        this.itemRepository            = itemRepository;
        this.itemAmountRepository      = itemAmountRepository;
        this.playerCharacterRepository = playerCharacterRepository;

    }


    public Iterable<Item> getAllItems() {

        return this.itemRepository.findAll();

    }


    public ItemModel addItem(ItemModel itemModel) throws DuplicateItemException, BadDataException {

        Item item = itemModel.convertToEntity();

        if (itemRepository.existsByName(item.getName())) {
            throw new DuplicateItemException(item.getName());      // Item med detta namn finns redan.
        }

        itemRepository.save(item);

        return itemModel;

    }


    public ItemModel getItemByName(String name) throws ItemNotFoundException {

        Optional<Item> optionalItem = itemRepository.findByName(name);

        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(name);     // Om det inte finns något item med detta namn.
        }

        return optionalItem.get().toModel();

    }


    public ItemModel updateItemData(ItemModel itemModel) throws ItemNotFoundException {

        Optional<Item> optionalItemToUpdate = itemRepository.findByName(itemModel.getName());

        if (optionalItemToUpdate.isEmpty()) {
            throw new ItemNotFoundException(itemModel.getName());     // Om det inte finns något item med detta namn.
        }

        Item itemToUpdate = optionalItemToUpdate.get();

        itemToUpdate.setPrice(itemModel.getPrice());
        itemToUpdate.setDamage(itemModel.getDamage());
        itemToUpdate.setHealthRecovery(itemModel.getHealthRecovery());
        itemToUpdate.setMaxAmount(itemModel.getMaxAmount());

        itemRepository.save(itemToUpdate);

        return itemToUpdate.toModel();

    }


    public void deleteItemByName(String name) throws ItemNotFoundException {

        if (itemRepository.existsByName(name)) {
            itemRepository.deleteByName(name);
        }
        else {
            throw new ItemNotFoundException(name);     // Om det inte finns något item med detta namn.
        }

    }


    public ItemAmountModel addItemToPlayerCharacter(ItemAmountModel itemAmountModel) throws PlayerNotFoundException, BadDataException, ItemNotFoundException {

        PlayerCharacter playerCharacter;
        Item item;
        Optional<ItemAmount> optionalItemAmount;
        ItemAmount itemAmount;

        if (itemAmountModel.getAmount() <= 0) {
            throw new BadDataException("The amount is invalid.");
        }


        playerCharacter = retrievePlayerCharacter(itemAmountModel.getCharacterName());

        item = retrieveItem(itemAmountModel.getItemName());


        optionalItemAmount = itemAmountRepository
                .findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isPresent()) {
            itemAmount = optionalItemAmount.get();
            itemAmount.setAmount(itemAmount.getAmount() + itemAmountModel.getAmount());
        }
        else {
            itemAmount = new ItemAmount(playerCharacter, item, itemAmountModel.getAmount());
        }

        // Kollar att antalet item inte överstiger maxAmount för detta item:

        if (itemAmount.getAmount() > item.getMaxAmount()) {
            throw new BadDataException("The amount is invalid.");
        }
        else {
            itemAmountRepository.save(itemAmount);
        }



        //System.out.println(playerCharacter);
        //System.out.println(item.toModel());
        //System.out.println(itemAmount);

        playerCharacter.getItemAmounts().forEach(itemAmount1 -> System.out.println(itemAmount1.getItem().getName() + ", " + itemAmount1.getAmount()));

        //System.out.println(playerCharacter.getItemAmounts().size());

        //System.out.println(playerCharacter.getCharacterName());

        //System.out.println(item.getName());

        return itemAmount.toModel();

    }


    public ItemAmountModel removeItemFromPlayerCharacter(ItemAmountModel itemAmountModel) throws PlayerNotFoundException, BadDataException, ItemNotFoundException, ItemAmountNotFoundException {

        PlayerCharacter playerCharacter;
        Item item;
        Optional<ItemAmount> optionalItemAmount;
        ItemAmount itemAmount;


        if (itemAmountModel.getAmount() <= 0) {
            throw new BadDataException("The amount is invalid.");
        }


        playerCharacter = retrievePlayerCharacter(itemAmountModel.getCharacterName());

        item = retrieveItem(itemAmountModel.getItemName());


        optionalItemAmount = itemAmountRepository
                .findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isPresent()) {
            itemAmount = optionalItemAmount.get();

            if (itemAmount.getAmount() > itemAmountModel.getAmount()) {
                itemAmount.setAmount(itemAmount.getAmount() - itemAmountModel.getAmount());
                itemAmountRepository.save(itemAmount);
                return itemAmount.toModel();        // Deleta 'itemAmount' om noll:
            }
            else if (itemAmount.getAmount() == itemAmountModel.getAmount()) {
                itemAmount.setAmount(0);
                itemAmountRepository.delete(itemAmount);
                return itemAmount.toModel();
            }
            else {
                throw new BadDataException(itemAmountModel.getCharacterName() +
                        " doesn't have the requested amount of the item '" +
                        itemAmountModel.getItemName() + "'.");      // Om 'itemAmount.getAmount() < amount'
            }
        }
        else {
            throw new ItemAmountNotFoundException(itemAmountModel.getCharacterName(),
                    itemAmountModel.getItemName());      // Om 'itemAmount' inte finns.
        }

    }



    private PlayerCharacter retrievePlayerCharacter(String characterName) throws PlayerNotFoundException, BadDataException {

        if (characterName == null) {
            throw new BadDataException("The name of the player character is missing.");
        }

        if (playerCharacterRepository.existsByCharacterName(characterName)) {
            return playerCharacterRepository.findByCharacterName(characterName);
        }
        else {
            throw new PlayerNotFoundException("A player character with the name '"
                    + characterName + "' could not be found.");
        }

    }


    private Item retrieveItem(String itemName) throws ItemNotFoundException, BadDataException {

        if (itemName == null) {
            throw new BadDataException("The name of the item is missing.");
        }

        Optional<Item> optionalItem = itemRepository.findByName(itemName);

        if (optionalItem.isEmpty()) {
            throw new ItemNotFoundException(itemName);    // Om det inte finns något item med detta namn.
        }
        else {
            return optionalItem.get();
        }

    }


    /*public int getItemAmount(String characterName, String itemName) throws PlayerNotFoundException, BadDataException, ItemNotFoundException {

        PlayerCharacter playerCharacter = retrievePlayerCharacter(characterName);

        Item item = retrieveItem(itemName);

        Optional<ItemAmount> optionalItemAmount =
                itemAmountRepository.findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isEmpty()) {
            return 0;
        }
        else {
            return optionalItemAmount.get().getAmount();
        }

    }*/


}
