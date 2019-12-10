package se.iths.complexjavaproject.mudders.model;


import lombok.*;
import se.iths.complexjavaproject.mudders.entity.NonPlayerCharacter;
import se.iths.complexjavaproject.mudders.entity.Town;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TownModel {

    private Long id;
    private String townName;
    private Set<String> npcs = new HashSet<>();

    public TownModel toModel(Town townToAdd){
        TownModel townModel = new TownModel();

        townModel.setTownName(townToAdd.getTownName());

        return townModel;
    }

    public TownModel toModelNpc( Town townToAddNpc){
        TownModel townModelNpc = new TownModel();

        townModelNpc.setTownName(townToAddNpc.getTownName());
        townToAddNpc.getNpcs().forEach(nonPlayerCharacter -> {
            townModelNpc.npcs.add(nonPlayerCharacter.getName());
        });
        return townModelNpc;
    }

}
