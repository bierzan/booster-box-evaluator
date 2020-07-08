package com.brzn.box_eval.mtg_io_client

import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray
import io.vavr.collection.HashSet
import io.vavr.collection.List
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class MtgIOTest extends Specification implements SampleCardSets {

    RestTemplate restTemplate = Mock(RestTemplate)
    MtgIOClient client = new MtgIOClient(new MtgIORestTemplate(restTemplate));
    MtgIO mtgIO = new MtgIO(client);

    def "should find list with single CardSet when Rest answered with array of one CardSet"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name))
        then:
        cardSets == List.of(sampleCommonSet)
    }

    def "should find list with multiple CardSets when Rest answered with array of many CardSets"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name, sampleMastersSet.name))
        then:
        cardSets == List.of(sampleCommonSet, sampleMastersSet)
    }

    def "should find empty list when invoked with list without names"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of())
        then:
        cardSets == List.empty()
    }

    def "should find empty list when invoked with null as list of names"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(null)
        then:
        cardSets == List.empty()
    }

    private void givenSetsToBeReturnedByRestTemplate(CardSet... sets) {
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Arrays.asList(sets))
    }
}