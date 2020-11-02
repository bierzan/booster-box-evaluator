package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import io.vavr.collection.List

@CompileStatic
class CardsFromResources {
    static List<CardDto> readJson2CardDtos(File file) {
        def mapper = new ObjectMapper()
        try {
            return List.of(mapper.readValue(file, CardDto[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return List.empty();
        }
    }
}
