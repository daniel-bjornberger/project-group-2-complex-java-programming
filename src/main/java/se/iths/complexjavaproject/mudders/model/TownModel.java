package se.iths.complexjavaproject.mudders.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TownModel {

    private String townName;
    private Set<String> npcs = new HashSet<>();

}
