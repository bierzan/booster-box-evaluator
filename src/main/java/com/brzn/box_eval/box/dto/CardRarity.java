package com.brzn.box_eval.box.dto;

public enum CardRarity {
    RARE("rare"),
    MYTHIC_RARE("mythic rare"),
    COMMON("common"),
    UNCOMMON("uncommon"),
    LAND("land");

    private final String rarityName;

    CardRarity(String rarityName) {
        this.rarityName = rarityName;
    }

    public String getRarityName() {
        return rarityName;
    }
}
