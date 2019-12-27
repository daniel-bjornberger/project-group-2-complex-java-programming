package se.iths.complexjavaproject.mudders.entity;

import lombok.Data;
import se.iths.complexjavaproject.mudders.model.ItemAmountModel;

import javax.persistence.*;

@Entity
@Table(name = "item_amount")
@Data
public class ItemAmount {

    @EmbeddedId
    private ItemAmountId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("player_character_id")
    @JoinColumn(name = "player_character_id")
    private PlayerCharacter playerCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("item_id")
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private int amount;


    public ItemAmount(PlayerCharacter playerCharacter, Item item, int amount) {

        this.playerCharacter = playerCharacter;
        this.item = item;
        this.id = new ItemAmountId(playerCharacter.getId(), item.getId());
        this.amount = amount;

    }

    public ItemAmountModel toModel() {

        return new ItemAmountModel(this.playerCharacter.getCharacterName(),
                this.item.getName(), this.amount);

    }

}
