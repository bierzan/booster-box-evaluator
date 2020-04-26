package com.brzn.box_eval.mtgIOclient.domain

import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.dto.Card
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleSets implements SampleCards {
    CardSet todaySet = from(todayCard)

    CardSet lastWeekSet = from(lastWeekCard)

    CardSet sampleCommonSet = CardSet.builder()
            .code("ss")
            .name("sampleSet")
            .releaseDate(LocalDate.now())
            .booster("boosterStructureAsString")
            .type(CardSetType.EXPANSION)
            .block("sampleBlock")
            .build();

    static private from(Card card) {
        return CardSet.builder()
                .releaseDate(card.releasedAt)
                .name(card.setName)
                .code(card.setCode)
                .type(CardSetType.EXPANSION)
                .block("")
                .booster(sampleBooster())
                .build()
    }

    static private String sampleBooster(){
        return "[  \n" +
                "      [  \n" +
                "          \"rare\",\n" +
                "          \"mythic rare\"\n" +
                "      ],\n" +
                "      \"uncommon\",\n" +
                "      \"uncommon\",\n" +
                "      \"uncommon\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"common\",\n" +
                "      \"land\",\n" +
                "      \"marketing\"\n" +
                "      ],"
    }
}


