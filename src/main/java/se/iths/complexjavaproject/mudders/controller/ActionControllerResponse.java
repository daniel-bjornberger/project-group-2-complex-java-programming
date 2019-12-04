package se.iths.complexjavaproject.mudders.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ActionControllerResponse {

    private String message;
    private boolean restButton;
    private boolean travelButton;
    private boolean attackButton;
    private boolean fleeButton;

}
