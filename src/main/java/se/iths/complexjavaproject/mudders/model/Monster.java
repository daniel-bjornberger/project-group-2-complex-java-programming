package se.iths.complexjavaproject.mudders.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="monster")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * Skapad av Elin och Tonny 26/11.
 * Monster class.
 */
public class Monster implements Serializable {

    private static final long serialVersionUID = -2799455774396393279L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="monsterType")
    private String monsterType;

    @Column(name="level")
    private int level;

    @Column(name="manaPoints")
    private int manaPoints;

    @Column(name="healthPoints")
    private int healthPoints;

    @Column(name="attackPoints")
    private int attackPoints;

    //experience points given to player after monster is defeated.
    @Column(name="experience")
    private int experience;

    //private List<Loot
    // > monsterLoot;

    public Monster toModel(){
        Monster monster = new Monster();

        return null;

    }

}
