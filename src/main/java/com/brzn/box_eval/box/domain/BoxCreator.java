package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.google.gson.Gson;

class BoxCreator {

    private Gson gson = new Gson();

    Box from(BoxDto boxDto) {
        return Box.builder()
                .id(boxDto.getId())
                .cardSetName(boxDto.getCardSetName())
                .booster(gson.toJson(boxDto.getBoosterStructure()))
                .releaseDate(boxDto.getReleaseDate())
                .build();
    }

    Box from(CardSet cardSet){
        return Box.builder()
                .releaseDate(cardSet.getReleaseDate())
                .cardSetName(cardSet.getName())
                .cardSetCode(cardSet.getCode())
                .build();
    }

}
