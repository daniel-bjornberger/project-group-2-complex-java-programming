package se.iths.complexjavaproject.mudders.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item_amount")
@Data
public class ItemAmount {

    @EmbeddedId
    private ItemAmountKey id;

    @ManyToOne
    @MapsId("player_character_id")
    @JoinColumn(name = "player_character_id")
    private PlayerCharacter playerCharacter;

    @ManyToOne
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private int amount;
    

}
