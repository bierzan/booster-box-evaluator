package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.BoxCardSetType
import com.brzn.box_eval.box.vo.BoosterSchema
import spock.lang.Specification

class BoxCreatorTest extends Specification implements SampleCardSetProperties {

    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator);

    def "should create Box from common CardSet"() {
        given:
        def boosterSchema = BoosterSchema.of(null)
        def expectedBox = Box.builder()
                .cardSetName(commonSet.name)
                .cardSetCode(commonSet.code)
                .boxCardSetType(BoxCardSetType.getMatchingType(commonSet.type.toString()))
                .releaseDate(commonSet.releaseDate)
                .boosterSchema(boosterSchema)
                .boosterQuantity(36)
                .build();

        boosterSchemaCreator.from(null) >> boosterSchema
        when:
        def resultBox = creator.from(commonSet)
        then:
        resultBox == expectedBox
    }
}
