package se.iths.complexjavaproject.mudders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.model.TownModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="towns")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Town implements Serializable {

    private static final long serialVersionUID = -7055707354641685721L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String townName;

    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
    private Set<NonPlayerCharacter> npcs = new HashSet<>();

    public TownModel toModel() {
        TownModel townModel = new TownModel();

       // townModel.setId(null);
        townModel.setTownName(getTownName());
        townModel.setNpcs(npcs.stream().map(NonPlayerCharacter::getName).collect(Collectors.toSet()));

        return townModel;
    }

  /*  public Town toEntity(TownModel townModel){
        Town town = new Town();
        town.setTownName(townModel.getTownName()
        );
        return town;

    }*/



    //player should have a Last Town variable that allows us to save info of the last town
    //player should automatically return to last town the next time they play.
    //Variable allows us to search List of Towns to see where they should go next.

}
