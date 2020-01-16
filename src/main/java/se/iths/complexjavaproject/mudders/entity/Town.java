package se.iths.complexjavaproject.mudders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.iths.complexjavaproject.mudders.model.TownModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "currentTown", cascade = CascadeType.ALL)
    private List<PlayerCharacter> players = new ArrayList<>();

    public TownModel toModel() {
        TownModel townModel = new TownModel();

        townModel.setTownName(getTownName());
        townModel.setNpcs(npcs.stream().map(NonPlayerCharacter::getName).collect(Collectors.toSet()));

        return townModel;
    }

}
