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

    @Column(name="name")
    private String name;

    @Column(name="level")
    private int level;

    @Column(name="health")
    private int health;

    @Column(name="damage")
    private int damage;

    @Column(name="givenExperience")
    private int givenExperience;

    //private List<Loot> monsterLoot;

    public Monster toModel(){
        Monster monster = new Monster();

        return null;

    }

}
