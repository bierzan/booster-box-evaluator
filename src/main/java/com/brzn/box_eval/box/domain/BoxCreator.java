package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.mtg_io_client.dto.CardSetType;
import lombok.AllArgsConstructor;

@AllArgsConstructor //todo definicja beana
class BoxCreator {

    private final BoosterSchemaCreator boosterSchemaCreator;

    Box from(CardSet cardSet) {
        return Box.builder()
                .releaseDate(cardSet.getReleaseDate())
                .cardSetName(cardSet.getName())
                .cardSetCode(cardSet.getCode())
                .boosterQuantity(getQuantityBySetType(cardSet))
                .boosterSchema(boosterSchemaCreator.from(cardSet.getBooster()))
                .build();
    }

    private short getQuantityBySetType(CardSet cardSet) { //todo unit test dla nulla
        if (cardSet.getBlock().equals("Conspiracy")) {
            return 24;
        } else {
            return (short) (cardSet.getType().equals(CardSetType.MASTERS) ? 24 : 36);
        }
    }

}
