package com.brzn.box_eval.box.domain

import com.brzn.box_eval.cache.CachedCards
import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import spock.lang.Specification

class BoxCreatorTest extends Specification implements SampleCardSets, CachedCards {

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
}
//todo pozostale pola do sprawdzenia
//todo sprawdzenie przemapowanie boosterow
