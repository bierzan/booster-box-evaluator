package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import spock.lang.Specification

import static com.brzn.box_eval.card.domain.CardJsonTestUtil.*

class CardMapperTest extends Specification {
    private File file
    private List<CardDto> cards
    def cardMapper = new CardMapper(new ObjectMapper())

    def cleanup() {
        deleteTemporaryJsons()
    }

    def "should parse json file to cards list"() {
        given:
        file = getBulkDataReferenceFile()
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.map({ c -> c.uuid }).sort() == getPropertiesFromCardJsonFile(file, "id").sort()
        cards.map({ c -> c.name }).sort() == getPropertiesFromCardJsonFile(file, "name").sort()
        cards.map({ c -> c.setName }).sort() == getPropertiesFromCardJsonFile(file, "set_name").sort()
        cards.map({ c -> c.setCode }).sort() == getPropertiesFromCardJsonFile(file, "set").sort()
        cards.map({ c -> c.releasedAt }).sort() == getDatePropertiesFromJsonFile(file, "released_at").sort()
        cards.map({ c -> c.price }).sort() == getPricesFromCardJsonFile(file).sort()
    }

    def "should return empty list when file is null"() {
        when:
        cards = cardMapper.fromJsonListFile(null)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to non existing file"() {
        given:
        file = new File("nonExistingFile.json")
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to different json array  file"() {
        given:
        file = getRandomJsonArray()
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when file refers to json with single object"() {
        given:
        file = getTempSingleCardJsonFromBulkDataReferenceFile()
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }

    def "should return empty list when filPath refers to unparseable file"() {
        given:
        file = getTempUnparseableJsonFile()
        when:
        cards = cardMapper.fromJsonListFile(file)
        then:
        cards.isEmpty()
    }
}
