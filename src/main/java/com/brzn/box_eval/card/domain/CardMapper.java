package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
class CardMapper {

    private final ObjectMapper mapper;

    public List<CardDto> fromJsonListFile(File file) {
        if (file == null) {
            log.info("requested File is null.");
            return List.empty();
        }

        try {
            return List.of(mapper.readValue(file, CardDto[].class))
                    .filter(CardDto::isValid);
        } catch (IOException e) {
            log.info("Can't parse json file to List<CardDto>");
            e.printStackTrace();
            return List.empty();
        }
    }
}
