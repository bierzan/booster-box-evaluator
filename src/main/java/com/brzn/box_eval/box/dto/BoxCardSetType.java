package com.brzn.box_eval.box.dto;

import java.util.Arrays;

public enum BoxCardSetType {
    CORE,
    EXPANSION,
    REPRINT,
    BOX,
    UN,
    FROM_THE_VAULT,
    PREMIUM_DECK,
    DUEL,
    STARTER,
    COMMANDER,
    PLANECHASE,
    ARCHENEMY,
    PROMO,
    VANGUARD,
    MASTERS;

    public static BoxCardSetType getMatchingType(String searchedType) {
        return Arrays.stream(BoxCardSetType.values())
                .filter(type -> type.toString().equals(searchedType))
                .findFirst()
                .orElse(null);
    }
}
