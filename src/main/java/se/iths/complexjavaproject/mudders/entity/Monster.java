package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.MonsterModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Skapad av Elin och Tonny 26/11.
 * Monster class.
 */

@Entity
@Table(name="monster")
@Data
public class Monster implements Serializable {

    private static final long serialVersionUID = -2799455774396393279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="level")
    private int level;

    @Column(name="health")
    private int health;

    @Column(name="damage")
    private int damage;

    @Column(name="given_experience")
    private int givenExperience;

    //private List<Loot> monsterLoot;

    public MonsterModel toModel(){
        return new MonsterModel(getName(), getLevel(), getHealth(), getDamage(), getGivenExperience());
    }

}
