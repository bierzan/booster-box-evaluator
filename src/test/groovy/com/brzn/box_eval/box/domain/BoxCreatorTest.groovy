package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.BoxCardSetType
import com.brzn.box_eval.box.vo.BoosterSchema
import com.brzn.box_eval.card.domain.CachedCards
import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import spock.lang.Specification

class BoxCreatorTest extends Specification implements SampleCardSets, CachedCards { //todo rename

    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator);

    def "should create Box from common CardSet"() {
        given:
        def boosterSchema = BoosterSchema.of(null)
        def expectedBox = Box.builder()
                .cardSetName(sampleCommonSet.name)
                .cardSetCode(sampleCommonSet.code)
                .boxCardSetType(BoxCardSetType.getMatchingType(sampleCommonSet.type.toString()))
                .releaseDate(sampleCommonSet.releaseDate)
                .boosterSchema(boosterSchema)
                .boosterQuantity(36)
                .build();

        boosterSchemaCreator.from(null) >> boosterSchema
        when:
        def resultBox = creator.from(sampleCommonSet)
        then:
        resultBox == expectedBox
    }
}
