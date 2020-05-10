package com.brzn.box_eval.mtgIOclient.domain

import com.brzn.box_eval.cache.CachedCards
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleCardSets implements CachedCards {

    CardSet sampleCommonSet = CardSet.builder()
            .code("ss")
            .name("sampleSet")
            .releaseDate(LocalDate.now())
            .booster("boosterStructureAsString")
            .type(CardSetType.EXPANSION)
            .block("sampleBlock")
            .build();


}


