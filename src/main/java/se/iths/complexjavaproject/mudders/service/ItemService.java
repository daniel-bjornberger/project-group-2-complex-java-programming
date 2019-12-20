package se.iths.complexjavaproject.mudders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.complexjavaproject.mudders.entity.Item;
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


    public void addItem(Item item) throws Exception {

        if (itemRepository.existsByName(item.getName())) {
            throw new Exception();
        }

        itemRepository.save(item);

    }


    public Item getItemByName(String name) throws Exception {

        Optional<Item> optionalItem = itemRepository.findByName(name);

        if (optionalItem.isEmpty()) {
            throw new Exception();
        }

        return optionalItem.get();

    }


    public void updateItemValue(Item item) throws Exception {

        /*if (itemRepository.existsByName(item.getName())) {
            Optional<Item> itemToUpdate = itemRepository.findByName(item.getName());
            itemToUpdate.setValue(item.getValue());
            itemRepository.save(itemToUpdate);
        }
        else {
            throw new Exception();
        }*/


        Optional<Item> optionalItemToUpdate = itemRepository.findByName(item.getName());

        if (optionalItemToUpdate.isEmpty()) {
            throw new Exception();
        }

        Item itemToUpdate = optionalItemToUpdate.get();
        itemToUpdate.setValue(item.getValue());
        itemRepository.save(itemToUpdate);

    }

    public void deleteItemByName(String name) throws Exception {

        if (itemRepository.existsByName(name)) {

            itemRepository.deleteByName(name);

        }
        else {
            throw new Exception();
        }

    }

}
