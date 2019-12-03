package se.iths.complexjavaproject.mudders.entity;

import lombok.Data;
import se.iths.complexjavaproject.mudders.model.NonPlayerCharacterModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "non_player_character")
@Data
public class NonPlayerCharacter implements Serializable {

    private static final long serialVersionUID = -7082664820089646376L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "town_id", nullable = false)
    private Town town;

    public NonPlayerCharacterModel toModel() {
        return new NonPlayerCharacterModel(getId(), getName());
    }
}
