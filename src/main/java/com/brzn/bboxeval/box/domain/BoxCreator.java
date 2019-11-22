package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import com.google.gson.Gson;

class BoxCreator {

    private Gson gson = new Gson();

    Box from(BoxDto boxDto) {
        return Box.builder()
                .id(boxDto.getId())
                .cardSetName(boxDto.getCardSetName())
                .booster(gson.toJson(boxDto.getBoosterStructure()))
                .build();
    }

}
