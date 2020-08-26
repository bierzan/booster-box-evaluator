package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxCardSetType;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.mtg_io_client.dto.CardSetType;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class BoxCreator {

    private final BoosterSchemaCreator boosterSchemaCreator;

    Box from(CardSet cardSet) {
        return Box.builder()
                .releaseDate(cardSet.getReleaseDate())
                .cardSetName(cardSet.getName())
                .cardSetCode(cardSet.getCode())
                .boxCardSetType(from(cardSet.getType()))
                .boosterSchema(boosterSchemaCreator.from(cardSet.getBooster()))
                .boosterQuantity(getBoosterQuantity(cardSet))
                .build();
    }

    private BoxCardSetType from(CardSetType cardSetType) {
        return Option.of(cardSetType)
                .map(Enum::toString)
                .map(BoxCardSetType::getMatchingType)
                .getOrNull();
    }

    private int getBoosterQuantity(CardSet cardSet) {
        if ("Conspiracy".equals(cardSet.getBlock())) {
            return 24;
        }
        return cardSet.getType() != null &&
                BoxCardSetType.MASTERS.toString().equals(cardSet.getType().toString()) ?
                24 : 36;
    }
}
