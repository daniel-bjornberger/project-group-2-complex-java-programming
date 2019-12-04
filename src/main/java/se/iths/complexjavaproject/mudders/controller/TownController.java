package se.iths.complexjavaproject.mudders.controller;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.complexjavaproject.mudders.entity.PlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.TownRepository;
import se.iths.complexjavaproject.mudders.service.TownService;

@RestController
@NoArgsConstructor
@RequestMapping("/town")
public class TownController {

    @Autowired
    private TownRepository townRepository ;

    @GetMapping(path = "/all")
    public ResponseEntity getAllTowns() {
        try{
            Iterable<Town> findAllTowns = townRepository.findAll();
            return ResponseEntity.ok().body(findAllTowns);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity addNewTown(@RequestBody String name){
        try{
            TownModel townModel = townRepository
                    .save(TownService.convertToEntity(name))
                    .toModel();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(townModel);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/delete/{townName}")
    public void removeTown(@PathVariable String townName){
        try{
            townRepository.deleteTownByName(townName);

        }catch (Exception e){
            ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
