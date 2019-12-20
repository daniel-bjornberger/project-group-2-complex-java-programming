package se.iths.complexjavaproject.mudders.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="monster")
@Data
public class Monster implements Serializable {

    private static final long serialVersionUID = -1170333618087042027L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //private List<Loot> monsterLoot;

}

