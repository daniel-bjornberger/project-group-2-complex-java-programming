package se.iths.complexjavaproject.mudders.model;


import lombok.*;
import se.iths.complexjavaproject.mudders.entity.Town;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TownModel {

    private Long id;
    private String name;

    public TownModel toModel(Town town){
        TownModel townModel = new TownModel();

        townModel.setName(town.getName());

        return townModel;
    }
}
