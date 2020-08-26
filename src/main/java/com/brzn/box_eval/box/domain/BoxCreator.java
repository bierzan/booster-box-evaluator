package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxCardSetType;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
class BoxCreator {

    private final BoosterSchemaCreator boosterSchemaCreator;

    Box from(CardSet cardSet) {
        return Box.builder()
                .releaseDate(cardSet.getReleaseDate())
                .cardSetName(cardSet.getName())
                .cardSetCode(cardSet.getCode())
                .cardSetBlock(cardSet.getBlock())
                .boxCardSetType(from(cardSet.getType()))
                .boosterSchema(boosterSchemaCreator.from(cardSet.getBooster()))
                .build();
    }

    private BoxCardSetType from(com.brzn.box_eval.mtg_io_client.dto.CardSetType cardSetType) {
        return Arrays.stream(BoxCardSetType.values())
                .filter(type -> type.toString().equals(cardSetType.toString()))
                .findFirst()
                .orElse(null);
    }
}
