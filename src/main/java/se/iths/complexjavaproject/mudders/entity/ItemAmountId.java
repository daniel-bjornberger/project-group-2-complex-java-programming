package se.iths.complexjavaproject.mudders.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemAmountId implements Serializable {

    private static final long serialVersionUID = 8466895055136103273L;

    @Column(name = "player_character_id")
    private Long playerCharacterId;

    @Column(name = "item_id")
    private Long itemId;

}
