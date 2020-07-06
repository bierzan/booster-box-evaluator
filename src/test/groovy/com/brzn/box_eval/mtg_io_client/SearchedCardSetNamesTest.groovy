package com.brzn.box_eval.mtg_io_client

import io.vavr.collection.HashSet
import spock.lang.Specification

class SearchedCardSetNamesTest extends Specification {
    def "hasValidSetNames should return true"() {
        given:
        def validNames = HashSet.of("sampleName")
        when:
        SearchedCardSetNames searchedNames = new SearchedCardSetNames(validNames)
        then:
        searchedNames.hasValidSetNames() == true
    }

    def "hasValidSetNames should return false when names are empty"() {
        when:
        SearchedCardSetNames searchedNames = new SearchedCardSetNames(HashSet.empty())
        then:
        searchedNames.hasValidSetNames() == false
    }

    def "hasValidSetNames should return false when invoked with null"() {
        when:
        SearchedCardSetNames searchedNames = new SearchedCardSetNames(null)
        then:
        searchedNames.hasValidSetNames() == false
    }

}
