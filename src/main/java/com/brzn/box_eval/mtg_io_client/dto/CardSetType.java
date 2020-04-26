package com.brzn.box_eval.mtg_io_client.dto;

public enum CardSetType {
    CORE ("core"),
    EXPANSION ("expansion"),
    REPRINT ("reprint"),
    BOX ("box"),
    UN("un"),
    FROM_THE_VAULT ("from the vault"),
    PREMIUM_DECK ("premium deck"),
    DUEL ("duel deck"),
    STARTER ("starter"),
    COMMANDER ("commander"),
    PLANECHASE ("planechase"),
    ARCHENEMY ("archenemy"),
    PROMO ("promo"),
    VANGUARD ("vanguard"),
    MASTERS ("masters");

    private String name;

    CardSetType(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
