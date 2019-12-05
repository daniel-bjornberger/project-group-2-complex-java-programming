package se.iths.complexjavaproject.mudders.controller;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.complexjavaproject.mudders.model.TownModel;
import se.iths.complexjavaproject.mudders.repository.TownRepository;
import se.iths.complexjavaproject.mudders.service.TownService;

@RestController
@NoArgsConstructor
@RequestMapping("/town")
public class TownController {

    @Autowired
    private TownRepository townRepository ;

//    ska hantera event i en stad. t.ex besöka en npc.

    /*@PostMapping(path = "/add")
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
    }*/
}
