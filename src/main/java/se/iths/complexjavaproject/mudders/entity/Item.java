package se.iths.complexjavaproject.mudders.entity;

import lombok.*;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;
import se.iths.complexjavaproject.mudders.model.ItemModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
//@Data
@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = -7643269416430085012L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "damage")
    private int damage;

    @Column(name = "health_recovery")
    private int healthRecovery;

    @Column(name = "max_amount")
    private int maxAmount;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemAmount> itemAmounts = new HashSet<>();


    public Item(String name, int price, int damage, int healthRecovery, int maxAmount) {
        this.name           = name;
        this.price          = price;
        this.damage         = damage;
        this.healthRecovery = healthRecovery;
        this.maxAmount      = maxAmount;
    }


    public ItemModel toModel() {

        ArrayList<ItemAmountModel> itemAmountModels = new ArrayList<>();

        this.itemAmounts.forEach(itemAmount -> itemAmountModels.add(itemAmount.toModel()));

        return new ItemModel(this.name, this.price,
                this.damage, this.healthRecovery, this.maxAmount, itemAmountModels);

    }

}
