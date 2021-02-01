package com.brzn.box_eval.card.domain

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.FileUtils
import spock.lang.Specification

class CardMapperTest extends Specification {
    def file = FileUtils.getFile("src/test/resources/cardBulkData.json")

    def cardMapper = new CardMapper(new ObjectMapper())

    def "should parse card name from json file"() {
        when:
        def cards = cardMapper.fromJsonListFile(file)
        then:
        1 == 4
    }
}
