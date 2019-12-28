package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;
import se.iths.complexjavaproject.mudders.model.ItemModel;
import se.iths.complexjavaproject.mudders.service.ItemService;

import java.util.ArrayList;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;


    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /*@Data
    private static class CharacterAndItemWrapper {
        private String characterName;
        private String itemName;
        private int amount;
    }*/


    @GetMapping(path = "/getall")
    public ResponseEntity getAllItems() {

        try {
            ArrayList<ItemModel> itemModels = new ArrayList<>();

            itemService.getAllItems().forEach(item -> itemModels.add(item.toModel()));

            return ResponseEntity.ok().body(itemModels);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @PostMapping(path = "/add")
    public ResponseEntity addItem(@RequestBody ItemModel itemModel) {

        try {
            //Item item = itemModel.convertToEntity();
            //itemService.addItem(itemModel);

            return ResponseEntity.ok().body(itemService.addItem(itemModel));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }


    @GetMapping(path = "/getbyname/{name}")
    public ResponseEntity getItemByName(@PathVariable("name") String name) {

        try {
            return ResponseEntity.ok().body(itemService.getItemByName(name));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @PutMapping(path = "/updateitemvalue")
    public ResponseEntity updateItemValue(@RequestBody ItemModel itemModel) {

        try {
            //itemService.updateItemValue(itemModel);

            return ResponseEntity.ok().body(itemService.updateItemValue(itemModel));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @DeleteMapping(path = "/deletebyname/{name}")
    public ResponseEntity deleteItemByName(@PathVariable("name") String name) {

        try {
            itemService.deleteItemByName(name);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping(path = "/additemtoplayercharacter")
    public ResponseEntity addItemToPlayerCharacter
            (@RequestBody ItemAmountModel itemAmountModel) {

        try {
            /*itemService.addItemToPlayerCharacter(itemAmountModel);*/
            return ResponseEntity.ok().body(itemService.addItemToPlayerCharacter(itemAmountModel));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
