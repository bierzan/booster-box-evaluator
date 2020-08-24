package com.brzn.box_eval.mtg_io_client.dto

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.Lists
import spock.lang.Specification

import java.time.LocalDate

class CardSetsArrayDeserializerTest extends Specification {

    private ObjectMapper mapper = new ObjectMapper();

    public static final String RELEASED = "2014-09-26"

    def "should deserialize releaseDate"() {
        given:
        String JSONresponse =
                "{\"sets\":[" +
                    "{" +
                        "\"releaseDate\":\"" + RELEASED + "\"" +
                    "}" +
                "]}";
        CardSet cardSet = CardSet.builder()
                .releaseDate(LocalDate.parse(RELEASED))
                .build()
        CardSetsArray cardSetsArray = new CardSetsArray(Lists.newArrayList(cardSet))
        when:
        def result = mapper.readValue(JSONresponse, CardSetsArray)
        then:
        result == cardSetsArray
    }

    def "should deserialize booster node"() {
        given:
        String JSONresponse =
                "{\"sets\":[" +
                    "{" +
                        "\"booster\":[" +
                            "[\"rare\",\"mythic rare\"]," +
                            "\"uncommon\"," +
                            "\"common\"" +
                        "]" +
                    "}" +
                "]}";

        String[][] booster = Lists.newArrayList(
                Lists.newArrayList("rare", "mythic rare").toArray(),
                Lists.newArrayList("uncommon").toArray(),
                Lists.newArrayList("common").toArray()
        ).toArray()
        CardSet cardSet = CardSet.builder()
                .booster(booster)
                .build()

        CardSetsArray cardSetsArray = new CardSetsArray(Lists.newArrayList(cardSet))
        when:
        def result = mapper.readValue(JSONresponse, CardSetsArray)
        then:
        result == cardSetsArray
    }

    //todo test na brak pola booster -1
    //todo test na booster w postaci pustej listy -1
}
