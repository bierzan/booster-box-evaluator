package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.CardSetProperties
import com.brzn.box_eval.box.dto.CardSetType
import spock.lang.Specification

class BoxCreatorBoosterQuantityTest extends Specification {
    public static final String STANDARD_BLOCK = "standardBlock"
    public static final String CONSPIRACY = "Conspiracy"
    def boosterSchemaCreator = Mock(BoosterSchemaCreator)
    def creator = new BoxCreator(boosterSchemaCreator)
    def box
    def cardSetProperties

    def "should create Box with boosterQuantity of 24 for Conspiracy block"() {
        given:
        giveCardSetPropertiesWithBlock(CONSPIRACY)
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 24
    }

    def "should create Box with boosterQuantity of 36 for standardBlock"() {
        given:
        giveCardSetPropertiesWithBlock(STANDARD_BLOCK)
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 36
    }

    def "should create Box with boosterQuantity of 24 for standardBlock and Masters set"() {
        given:
        giveCardSetPropertiesWithBlockAndType(STANDARD_BLOCK, CardSetType.MASTERS)
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 24
    }


    def "should create Box with boosterQuantity of 36 for standardBlock and any other set"() {
        given:
        giveCardSetPropertiesWithBlockAndType(STANDARD_BLOCK, CardSetType.CORE)
        when:
        createsBoosterFromCardSet()
        then:
        getBoosterQuantity() == 36
    }

    def giveCardSetPropertiesWithBlock(String block) {
        cardSetProperties = CardSetProperties.builder()
                .block(block)
                .build()
    }

    def giveCardSetPropertiesWithBlockAndType(String block, CardSetType type) {
        cardSetProperties = CardSetProperties.builder()
                .block(block)
                .type(type)
                .build()
    }

    def createsBoosterFromCardSet() {
        box = creator.from(cardSetProperties as CardSetProperties)
    }

    private int getBoosterQuantity() {
        (box as Box).dto().boosterQuantity
    }

    def verifyBoosterQuantity(int expectedQuantity) {
        return (box as Box).dto().boosterQuantity == expectedQuantity
    }
}
