package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.PlayerCharacterModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

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

    @Column(name = "character_name", unique = true)
    private String characterName;

    @Column(name = "experience")
    private int experience = 0;

    @Column(name = "level")
    private int level = 1;

    @Column(name = "health")
    private int health = 10;

    @Column(name = "damage")
    private int damage = 1;

    @Column(name = "currency")
    private int currency = 100;

    @Column(name = "max_health")
    private int maxHealth = 10;
    
    private boolean inCombat = false;

    @OneToOne
    @JoinColumn(name = "town_id")
    private Town currentTown;

    /*TODO: Add one to one relation with user to keep track of owner
    @Column(name = "user_id")
    private Long id;
    */

    // TODO: Kolumn-namn skrivs med "snake_case"? character_name, home_town?   /Daniel
    // https://stackoverflow.com/questions/26535614/jpa-naming-convention
    // https://vladmihalcea.com/map-camel-case-properties-snake-case-column-names-hibernate/

    //TODO: Monster entity

    public PlayerCharacterModel toModel() {
        PlayerCharacterModel playerCharacterModel = new PlayerCharacterModel();

        playerCharacterModel.setCharacterName(getCharacterName());
        playerCharacterModel.setExperience(getExperience());
        playerCharacterModel.setLevel(getLevel());
        playerCharacterModel.setHealth(getHealth());
        playerCharacterModel.setCurrentTown(getCurrentTown());
        playerCharacterModel.setDamage(getDamage());
        playerCharacterModel.setCurrency(getCurrency());

        return playerCharacterModel;
    }

}
