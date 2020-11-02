package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
class CardMapper {

    private final ObjectMapper mapper;

    public List<CardDto> fromJsonListFile(File file) {
        try {
            return List.of(mapper.readValue(file, CardDto[].class));
        } catch (IOException e) { //todo unit test
            log.info("Can't parse json file to <List<Card>>. CardCache update failed");
            e.printStackTrace();
            return List.empty();
        }
    }
}
