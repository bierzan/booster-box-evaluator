package com.brzn.box_eval.mtg_io_client

import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray
import io.vavr.collection.HashSet
import io.vavr.collection.List
import org.assertj.core.util.Lists
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class MtgIOTest extends Specification implements SampleCardSets {

    RestTemplate restTemplate = Mock(RestTemplate)
    MtgIOClient client = new MtgIOClient(restTemplate);
    MtgIO mtgIO = new MtgIO(client);

    def "should find list with single CardSet when Rest answered with array of one CardSet"() {
        given:
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Lists.newArrayList(sampleCommonSet));
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name))
        then:
        cardSets == List.of(sampleCommonSet)
    }

    def "should find list with multiple CardSets when Rest answered with array of many CardSets"() {
        given:
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Lists.newArrayList(sampleCommonSet, sampleMastersSet));
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of(sampleCommonSet.name, sampleMastersSet.name))
        then:
        cardSets == List.of(sampleCommonSet, sampleMastersSet)
    }

    def "should find empty list when invoked with list without names"() {
        given:
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Lists.newArrayList(sampleCommonSet, sampleMastersSet));
        when:
        def cardSets = mtgIO.findCardSetsByName(HashSet.of())
        then:
        cardSets == Collections.emptyList()
    }

    def "should find empty list when invoked with null as list of names"() {
        given:
        restTemplate.getForObject(_ as String, CardSetsArray.class) >> new CardSetsArray(Lists.newArrayList(sampleCommonSet, sampleMastersSet));
        when:
        def cardSets = mtgIO.findCardSetsByName(null)
        then:
        cardSets == Collections.emptyList()
    }
}