package se.iths.complexjavaproject.mudders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NonPlayerCharacterModel implements Serializable {

    private static final long serialVersionUID = -4298562145638460068L;

    private Long id;
    private String name;

}
