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


    public ItemModel addItem(ItemModel itemmodel) throws Exception {

        Item item = itemmodel.convertToEntity();

        if (itemRepository.existsByName(item.getName())) {
            throw new Exception();
        }

        itemRepository.save(item);

        return itemmodel;

    }


    public ItemModel getItemByName(String name) throws Exception {

        Optional<Item> optionalItem = itemRepository.findByName(name);

        if (optionalItem.isEmpty()) {
            throw new Exception();
        }

        return optionalItem.get().toModel();

    }


    public ItemModel updateItemValue(ItemModel itemModel) throws Exception {

        /*if (itemRepository.existsByName(item.getName())) {
            Optional<Item> itemToUpdate = itemRepository.findByName(item.getName());
            itemToUpdate.setValue(item.getValue());
            itemRepository.save(itemToUpdate);
        }
        else {
            throw new Exception();
        }*/


        // TODO: RETURNERA ItemModel?


        Optional<Item> optionalItemToUpdate = itemRepository.findByName(itemModel.getName());

        if (optionalItemToUpdate.isEmpty()) {
            throw new Exception();
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
            throw new Exception();
        }

    }


    public ItemAmountModel addItemToPlayerCharacter(String characterName, String itemName, int amount) throws Exception {

        PlayerCharacter playerCharacter;
        Optional<Item> optionalItem;
        Item item;
        Optional<ItemAmount> optionalItemAmount;
        ItemAmount itemAmount;

        if (characterName == null || itemName == null) {
            throw new BadDataException("At least one of the required strings is missing.");
        }

        if (amount <= 0) {
            throw new BadDataException("The amount is invalid.");
        }

        if (playerCharacterRepository.existsByCharacterName(characterName)) {
            playerCharacter = playerCharacterRepository.findByCharacterName(characterName);
        }
        else {
            throw new PlayerNotFoundException("A player character with the name '"
                    + characterName + "' could not be found.");
        }

        optionalItem = itemRepository.findByName(itemName);

        if (optionalItem.isEmpty()) {
            throw new Exception();
        }
        else {
            item = optionalItem.get();
        }

        optionalItemAmount = itemAmountRepository
                .findById(new ItemAmountId(playerCharacter.getId(), item.getId()));

        if (optionalItemAmount.isPresent()) {
            itemAmount = optionalItemAmount.get();
            itemAmount.setAmount(itemAmount.getAmount() + amount);
        }
        else {
            itemAmount = new ItemAmount(playerCharacter, item, amount);
        }

        itemAmountRepository.save(itemAmount);

        return itemAmount.toModel();

    }

}
