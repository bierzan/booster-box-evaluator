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
    MtgIO mtgIO = new MtgIO(new MtgIORestTemplate(restTemplate));

    def "findCardSetsByName should find list with single CardSet when Rest answered with array of one CardSet"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name))
        then:
        cardSets == List.of(sampleCommonSet)
    }

    def "findCardSetsByName should find list with multiple CardSets when Rest answered with array of many CardSets"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name, sampleMastersSet.name))
        then:
        cardSets == List.of(sampleCommonSet, sampleMastersSet)
    }

    def "findCardSetsByName should find empty list when invoked with list without names"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of())
        then:
        cardSets == List.empty()
    }

    def "findCardSetsByName should find empty list when invoked with null as list of names"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findCardSetsByName(null)
        then:
        cardSets == List.empty()
    }

    def "findAllCardSets should find list with multiple CardSets when Rest answered with array of many CardSets"() {
        given:
        givenSetsToBeReturnedByRestTemplate(sampleCommonSet, sampleMastersSet);
        when:
        def cardSets = mtgIO.findAllCardSets()
        then:
        cardSets == List.of(sampleCommonSet, sampleMastersSet)
    }

    private void givenSetsToBeReturnedByRestTemplate(CardSet... sets) {
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Arrays.asList(sets))
    }
}