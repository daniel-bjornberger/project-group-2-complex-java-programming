package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Item;
import se.iths.complexjavaproject.mudders.entity.ItemAmount;
import se.iths.complexjavaproject.mudders.entity.ItemAmountId;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.exception.BadDataException;
import se.iths.complexjavaproject.mudders.exception.PlayerNotFoundException;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;
import se.iths.complexjavaproject.mudders.model.ItemModel;
import se.iths.complexjavaproject.mudders.repository.ItemAmountRepository;
import se.iths.complexjavaproject.mudders.repository.ItemRepository;
import se.iths.complexjavaproject.mudders.repository.PlayerCharacterRepository;

import java.util.Optional;

@Service
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


    public ItemModel addItem(ItemModel itemModel) throws Exception {

        Item item = itemModel.convertToEntity();

        if (itemRepository.existsByName(item.getName())) {
            throw new Exception();          // TODO: Item med detta namn finns redan.
        }

        itemRepository.save(item);

        return itemModel;

    }


    public ItemModel getItemByName(String name) throws Exception {

        Optional<Item> optionalItem = itemRepository.findByName(name);

        if (optionalItem.isEmpty()) {
            throw new Exception();     // TODO: Om det inte finns något item med detta namn.
        }

        return optionalItem.get().toModel();

    }


    public ItemModel updateItemValue(ItemModel itemModel) throws Exception {

        Optional<Item> optionalItemToUpdate = itemRepository.findByName(itemModel.getName());

        if (optionalItemToUpdate.isEmpty()) {
            throw new Exception();     // TODO: Om det inte finns något item med detta namn.
        }

        Item itemToUpdate = optionalItemToUpdate.get();
        itemToUpdate.setValue(itemModel.getValue());
        itemRepository.save(itemToUpdate);

        return itemToUpdate.toModel();

    }


    public void deleteItemByName(String name) throws Exception {

        if (itemRepository.existsByName(name)) {
            itemRepository.deleteByName(name);
        }
        else {
            throw new Exception();     // TODO: Om det inte finns något item med detta namn.
        }

    }


    public ItemAmountModel addItemToPlayerCharacter(ItemAmountModel itemAmountModel) throws Exception {

        PlayerCharacter playerCharacter;
        //Optional<Item> optionalItem;
        Item item;
        Optional<ItemAmount> optionalItemAmount;
        ItemAmount itemAmount;

        if (itemAmountModel.getAmount() <= 0) {
            throw new BadDataException("The amount is invalid.");
        }


        /*if (characterName == null || itemName == null) {
            throw new BadDataException("At least one of the required strings is missing.");
        }

        if (amount <= 0) {
            throw new BadDataException("The amount is invalid.");
        }*/


        /*if (playerCharacterRepository.existsByCharacterName(characterName)) {
            playerCharacter = playerCharacterRepository.findByCharacterName(characterName);
        }
        else {
            throw new PlayerNotFoundException("A player character with the name '"
                    + characterName + "' could not be found.");
        }*/


        playerCharacter = retrievePlayerCharacter(itemAmountModel);


        /*optionalItem = itemRepository.findByName(itemName);

        if (optionalItem.isEmpty()) {
            throw new Exception();
        }
        else {
            item = optionalItem.get();
        }*/

        item = retrieveItem(itemAmountModel);


        optionalItemAmount = itemAmountRepository
                .findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isPresent()) {
            itemAmount = optionalItemAmount.get();
            itemAmount.setAmount(itemAmount.getAmount() + itemAmountModel.getAmount());
        }
        else {
            itemAmount = new ItemAmount(playerCharacter, item, itemAmountModel.getAmount());
        }

        itemAmountRepository.save(itemAmount);

        return itemAmount.toModel();

    }


    public ItemAmountModel removeItemFromPlayerCharacter(ItemAmountModel itemAmountModel) throws Exception {

        PlayerCharacter playerCharacter;
        Item item;
        Optional<ItemAmount> optionalItemAmount;
        ItemAmount itemAmount;


        if (itemAmountModel.getAmount() <= 0) {
            throw new BadDataException("The amount is invalid.");
        }

        playerCharacter = retrievePlayerCharacter(itemAmountModel);

        item = retrieveItem(itemAmountModel);

        optionalItemAmount = itemAmountRepository
                .findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isPresent()) {
            itemAmount = optionalItemAmount.get();

            if (itemAmount.getAmount() >= itemAmountModel.getAmount()) {
                itemAmount.setAmount(itemAmount.getAmount() - itemAmountModel.getAmount());
                itemAmountRepository.save(itemAmount);
                return itemAmount.toModel();
            }
            else {
                throw new Exception();      // TODO: Om 'itemAmount.getAmount() < amount'
            }
        }
        else {
            //itemAmount = new ItemAmount(playerCharacter, item, amount);
            throw new Exception();      // TODO: Om 'optionalItemAmount' inte finns.
        }

        /*itemAmountRepository.save(itemAmount);

        return itemAmount.toModel();*/

    }



    private PlayerCharacter retrievePlayerCharacter(ItemAmountModel itemAmountModel) throws PlayerNotFoundException, BadDataException {

        if (itemAmountModel.getCharacterName() == null) {
            throw new BadDataException("The name of the player character is missing.");
        }

        if (playerCharacterRepository.existsByCharacterName(itemAmountModel.getCharacterName())) {
            return playerCharacterRepository.findByCharacterName(itemAmountModel.getCharacterName());
        }
        else {
            throw new PlayerNotFoundException("A player character with the name '"
                    + itemAmountModel.getCharacterName() + "' could not be found.");
        }

    }


    private Item retrieveItem(ItemAmountModel itemAmountModel) throws Exception {

        if (itemAmountModel.getItemName() == null) {
            throw new BadDataException("The name of the item is missing.");
        }

        Optional<Item> optionalItem = itemRepository.findByName(itemAmountModel.getItemName());

        if (optionalItem.isEmpty()) {
            throw new Exception();    // TODO: Om det inte finns något item med detta namn.
        }
        else {
            return optionalItem.get();
        }

    }


}
