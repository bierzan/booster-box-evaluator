package com.brzn.box_eval.adapter.cardsetproperties;

import com.brzn.box_eval.box.dto.CardSetProperties;
import com.brzn.box_eval.box.dto.CardSetType;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;

class CardSetMapper {
    public CardSetProperties convertToCardSetProperties(CardSet cardSet) {
        return CardSetProperties.builder()
                .name(cardSet.getName())
                .block(cardSet.getBlock())
                .booster(cardSet.getBooster())
                .code(cardSet.getCode())
                .releaseDate(cardSet.getReleaseDate())
                .type(CardSetType.valueOf(cardSet.getType().toString()))
                .build();
    }

}
