package se.iths.complexjavaproject.mudders.dto;

import se.iths.complexjavaproject.mudders.exception.UnsupportedObjectException;

public interface ICombatActions {

    int attack(Object target) throws UnsupportedObjectException;

    int flee();


}
