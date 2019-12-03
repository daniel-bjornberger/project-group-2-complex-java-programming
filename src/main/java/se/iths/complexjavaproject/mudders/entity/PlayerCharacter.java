package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "player_character")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PlayerCharacter implements Serializable {

    private static final long serialVersionUID = 2982112410056449932L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     // TODO: AUTO eller IDENTITY? Borde vara samma för alla models, eller? /Daniel
    @Column(name = "id")
    private Long id;

    @Column(name = "characterName")
    private String characterName;

    @Column(name = "experience")
    private int experience = 0;

    @Column(name = "level")
    private int level = 1;

    @Column(name = "health")
    private int health = 10;

    @Column(name = "mana")
    private int mana = 10;

    @Column(name = "homeTown")
    private String homeTown = "VillageOne";

    // TODO: Kolumn-namn skrivs med "snake_case"? character_name, home_town?   /Daniel
    // https://stackoverflow.com/questions/26535614/jpa-naming-convention
    // https://vladmihalcea.com/map-camel-case-properties-snake-case-column-names-hibernate/

    @Column(name = "damage")
    private int damage = 1;

    public PlayerCharacterModel toModel() {
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.setId(null);
        playerCharacterModel.setCharacterName(getCharacterName());
        playerCharacterModel.setExperience(getExperience());
        playerCharacterModel.setLevel(getLevel());
        playerCharacterModel.setHealth(getHealth());
        playerCharacterModel.setMana(getMana());
        playerCharacterModel.setHomeTown(getHomeTown());
        playerCharacterModel.setDamage(getDamage());

        return playerCharacterModel;
    }

}
