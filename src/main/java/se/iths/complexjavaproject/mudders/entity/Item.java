package se.iths.complexjavaproject.mudders.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "item")
@Data
public class Item implements Serializable {

    private static final long serialVersionUID = -7643269416430085012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "value")
    private int value;

}
