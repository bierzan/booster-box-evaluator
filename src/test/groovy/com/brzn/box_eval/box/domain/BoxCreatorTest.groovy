package com.brzn.box_eval.box.domain

import com.brzn.box_eval.mtgIOclient.domain.SampleSets
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.dto.Card
import spock.lang.Specification

class BoxCreatorTest extends Specification implements SampleSets, SampleCards {

    def creator = new BoxCreator();

    def "should create Box from common CardSet"() {
        given:

        when:
        def resultBox = creator.from(sampleCommonSet)
        then:
        resultBox.cardSetName == sampleCommonSet.name;
        resultBox.releaseDate == sampleCommonSet.releaseDate
        resultBox.cardSetCode == sampleCommonSet.code;
        resultBox.boosterQuantity == (short) 36;
    }

    @Override
    Card getTodayCard() {
        return SampleCards.super.getTodayCard()
    }

    @Override
    void setTodayCard(Card todayCard) {
        SampleCards.super.setTodayCard(todayCard)
    }

    @Override
    Card getLastWeekCard() {
        return SampleCards.super.getLastWeekCard()
    }

    @Override
    void setLastWeekCard(Card lastWeekCard) {
        SampleCards.super.setLastWeekCard(lastWeekCard)
    }
}
//todo pozostale pola do sprawdzenia
//todo sprawdzenie przemapowanie boosterow
