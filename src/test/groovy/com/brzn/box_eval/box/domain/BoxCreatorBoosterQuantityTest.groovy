package com.brzn.box_eval.box.domain

import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import spock.lang.Specification

class BoxCreatorBoosterQuantityTest extends Specification {
    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator)

    def "should create Box with boosterQuantity of 24 for Conspiracy block"(){
        given:
        def cardSet = CardSet.builder()
                .block("Conspiracy")
                .build()
        when:
        Box box = creator.from(cardSet)
        then:
        box.getBoosterQuantity() == 24
    }

    def "should create Box with boosterQuantity of 36 for standardBlock"(){
        given:
        def cardSet = CardSet.builder()
                .block("standardBlock")
                .build()
        when:
        Box box = creator.from(cardSet)
        then:
        box.getBoosterQuantity() == 36
    }

    def "should create Box with boosterQuantity of 24 for standardBlock and Masters set"(){
        given:
        def cardSet = CardSet.builder()
                .block("standardBlock")
                .type(CardSetType.MASTERS)
                .build()
        when:
        Box box = creator.from(cardSet)
        then:
        box.getBoosterQuantity() == 24
    }

    def "should create Box with boosterQuantity of 36 for standardBlock and any other set"(){
        given:
        def cardSet = CardSet.builder()
                .block("standardBlock")
                .type(CardSetType.CORE)
                .build()
        when:
        Box box = creator.from(cardSet)
        then:
        box.getBoosterQuantity() == 36
    }
}
