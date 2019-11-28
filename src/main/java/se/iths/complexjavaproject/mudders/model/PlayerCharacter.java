package se.iths.complexjavaproject.mudders.model;

import lombok.*;
import net.minidev.json.writer.JsonReader;
import javax.persistence.*;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public PlayerCharacter toConvertPlayerCharacter(){
        PlayerCharacter playerCharacter = new PlayerCharacter();

        return playerCharacter;
    }

}
