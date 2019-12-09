package se.iths.complexjavaproject.mudders.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServerResponse {

    private String message;
    private boolean restButton;
    private boolean travelButton;
    private boolean attackButton;
    private boolean fleeButton;         // Mer parametrar tillkommer förmodligen.   /Daniel

}
