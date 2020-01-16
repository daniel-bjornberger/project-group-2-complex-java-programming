package se.iths.complexjavaproject.mudders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.exception.*;
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


    @GetMapping(path = "/getall")
    public ResponseEntity<?> getAllItems() {

        try {
            ArrayList<ItemModel> itemModels = new ArrayList<>();

            itemService.getAllItems().forEach(item -> itemModels.add(item.toModel()));

            return ResponseEntity.ok().body(itemModels);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @PostMapping(path = "/add")
    public ResponseEntity<?> addItem(@RequestBody ItemModel itemModel) {

        try {
            return ResponseEntity.ok().body(itemService.addItem(itemModel));
        }
        catch (DuplicateItemException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @GetMapping(path = "/getbyname/{name}")
    public ResponseEntity<?> getItemByName(@PathVariable("name") String name) {

        try {
            return ResponseEntity.ok().body(itemService.getItemByName(name));
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @PutMapping(path = "/updateitemdata")
    public ResponseEntity<?> updateItemData(@RequestBody ItemModel itemModel) {

        try {
            return ResponseEntity.ok().body(itemService.updateItemData(itemModel));
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @DeleteMapping(path = "/deletebyname/{name}")
    public ResponseEntity<?> deleteItemByName(@PathVariable("name") String name) {

        try {
            itemService.deleteItemByName(name);

            // TODO: Behöver man kolla om en item ingår i en itemAmount, innan man deletar den?

            return ResponseEntity.ok().build();
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


    @PostMapping(path = "/additemtoplayercharacter")
    public ResponseEntity<?> addItemToPlayerCharacter(@RequestBody ItemAmountModel itemAmountModel) {

        try {
            return ResponseEntity.ok().body(itemService.addItemToPlayerCharacter(itemAmountModel));
        }
        catch (PlayerNotFoundException | ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    @PostMapping(path = "/removeitemfromplayercharacter")
    public ResponseEntity<?> removeItemFromPlayerCharacter(@RequestBody ItemAmountModel itemAmountModel) {

        try {
            return ResponseEntity.ok().body(itemService.removeItemFromPlayerCharacter(itemAmountModel));
        }
        catch (PlayerNotFoundException | ItemNotFoundException | ItemAmountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


    /*@GetMapping(path = "/getitemamount")
    public ResponseEntity<?> getItemAmount(@RequestParam("charactername") String characterName,
                                        @RequestParam("itemname") String itemName) {

        try {
            return ResponseEntity.ok()
                    .body(itemService.getItemAmount(characterName, itemName));
        }
        catch (PlayerNotFoundException | ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (BadDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }*/

}
