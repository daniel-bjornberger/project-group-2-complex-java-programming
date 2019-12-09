package se.iths.complexjavaproject.mudders.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientResponse {

    private Integer option;
    private String characterName;           // Mer parametrar tillkommer förmodligen.   /Daniel


    public boolean empty() {

        return this.characterName == null || this.characterName.isBlank() || this.option == null;

    }

}
