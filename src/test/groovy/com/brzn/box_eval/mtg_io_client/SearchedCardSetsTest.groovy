package com.brzn.box_eval.mtg_io_client

import io.vavr.collection.HashSet
import spock.lang.Specification

class SearchedCardSetsTest extends Specification {
    def "hasValidSetNames should return true"() {
        given:
        def validNames = HashSet.of("sampleName")
        when:
        SearchedCardSets searchedNames = new SearchedCardSets(validNames)
        then:
        searchedNames.hasValidSetNames() == true
    }

    def "hasValidSetNames should return false when names are empty"() {
        when:
        SearchedCardSets searchedNames = new SearchedCardSets(HashSet.empty())
        then:
        searchedNames.hasValidSetNames() == false
    }

    def "hasValidSetNames should return false when invoked with null"() {
        when:
        SearchedCardSets searchedNames = new SearchedCardSets(null)
        then:
        searchedNames.hasValidSetNames() == false
    }

}
