package com.brzn.box_eval.box.domain

import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import spock.lang.Specification

class BoxCreatorBoosterQuantityTest extends Specification {
    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator)
    def box
    def cardSet

    def "should create Box with boosterQuantity of 24 for Conspiracy block"() {
        given:
        cardSet = CardSet.builder()
                .block("Conspiracy")
                .build()
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 24
    }

    def "should create Box with boosterQuantity of 36 for standardBlock"() {
        given:
        cardSet = CardSet.builder()
                .block("standardBlock")
                .build()
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 36
    }

    def "should create Box with boosterQuantity of 24 for standardBlock and Masters set"() {
        given:
        cardSet = CardSet.builder()
                .block("standardBlock")
                .type(CardSetType.MASTERS)
                .build()
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 24
    }


    def "should create Box with boosterQuantity of 36 for standardBlock and any other set"() {
        given:
        cardSet = CardSet.builder()
                .block("standardBlock")
                .type(CardSetType.CORE)
                .build()
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 36
    }

    def createsBoosterFromCardSet() {
        box = creator.from(cardSet as CardSet)
    }

    private int getBoosterQuantity() {
        (box as Box).dto().boosterQuantity
    }

    def verifyBoosterQuantity(int expectedQuantity) {
        return (box as Box).dto().boosterQuantity == expectedQuantity
    }
}
