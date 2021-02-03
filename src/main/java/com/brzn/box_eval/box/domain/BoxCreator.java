package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxCardSetType;
import com.brzn.box_eval.box.dto.CardSetProperties;
import com.brzn.box_eval.box.dto.CardSetType;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class BoxCreator {

    private final BoosterSchemaCreator boosterSchemaCreator;

    Box from(CardSetProperties cardSetProperties) {
        return Box.builder()
                .releaseDate(cardSetProperties.getReleaseDate())
                .cardSetName(cardSetProperties.getName())
                .cardSetCode(cardSetProperties.getCode())
                .boxCardSetType(from(cardSetProperties.getType()))
                .boosterSchema(boosterSchemaCreator.from(cardSetProperties.getBooster()))
                .boosterQuantity(getBoosterQuantity(cardSetProperties))
                .build();
    }

    private BoxCardSetType from(CardSetType cardSetType) {
        return Option.of(cardSetType)
                .map(Enum::toString)
                .map(BoxCardSetType::getMatchingType)
                .getOrNull();
    }

    private int getBoosterQuantity(CardSetProperties cardSetProperties) {
        if ("Conspiracy".equals(cardSetProperties.getBlock())) {
            return 24;
        }
        return cardSetProperties.getType() != null &&
                BoxCardSetType.MASTERS.toString().equals(cardSetProperties.getType().toString()) ?
                24 : 36;
    }
}
