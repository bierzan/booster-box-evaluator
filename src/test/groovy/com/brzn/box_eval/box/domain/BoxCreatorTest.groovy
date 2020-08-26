package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.vo.BoosterSchema
import com.brzn.box_eval.cache.CachedCards
import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import io.vavr.collection.List
import spock.lang.Specification

class BoxCreatorTest extends Specification implements SampleCardSets, CachedCards {

    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator);

    def "should create Box from common CardSet"() {
        given:
        boosterSchemaCreator.from(_ as String[][]) >> BoosterSchema.of(List.empty())
        when:
        def resultBox = creator.from(sampleCommonSet)
        then:
        resultBox.cardSetName == sampleCommonSet.name;
        resultBox.releaseDate == sampleCommonSet.releaseDate
        resultBox.cardSetCode == sampleCommonSet.code;
    }
}
//todo pozostale pola do sprawdzenia
