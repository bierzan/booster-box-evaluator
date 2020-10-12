package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.CardSetProperties
import com.brzn.box_eval.box.dto.CardSetType
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleCardSetProperties {
    CardSetProperties todayCardSet = CardSetProperties.builder()
            .name("todayCardSet")
            .code("ts")
            .releaseDate(LocalDate.now())
            .build();


    CardSetProperties lastWeekCardSet = CardSetProperties.builder()
            .name("lastWeekCardSet")
            .code("lws")
            .releaseDate(LocalDate.now().minusWeeks(1))
            .build();

    CardSetProperties commonSet = CardSetProperties.builder()
            .code("ss")
            .name("sampleSet")
            .releaseDate(LocalDate.now())
            .type(CardSetType.EXPANSION)
            .block("sampleBlock")
            .build();

    CardSetProperties mastersSet = CardSetProperties.builder()
            .code("ms")
            .name("masterSet")
            .releaseDate(LocalDate.now())
            .type(CardSetType.MASTERS)
            .block("sampleMastersBlock")
            .build();
}
