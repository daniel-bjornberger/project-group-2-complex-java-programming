package se.iths.complexjavaproject.mudders.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/combat")
public class CombatController {

    @GetMapping
    public ResponseEntity testRequest() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Empty");
    }
}
