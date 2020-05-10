package com.brzn.box_eval.mtg_io_client

import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import io.vavr.collection.List
import spock.lang.Specification

class MtgIOTest extends Specification implements SampleCardSets {

    MtgIO mtgIO = new MtgIO()

    def "should find CardSets by list of CardSets names"() {
        given:
        def cardSetNames = List.of(sampleCommonSet.name).toSet()
        when:
        def cardSets = mtgIO.findCardSetsByName(cardSetNames)
        then:
        cardSets.map({ set -> set.name }).contains(sampleCommonSet.name)
    }
}
