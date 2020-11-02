package com.brzn.box_eval.card.domain


import com.brzn.box_eval.card.port.CardJsonFileProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.FileUtils
import spock.lang.Specification

import java.time.LocalDate

class CardUpdaterTest extends Specification {
    def fileProvider = Mock(CardJsonFileProvider)
    def mapper = new CardMapper(new ObjectMapper())
    def repository = Mock(CardRepository)
    def updater = new CardUpdater(repository, mapper, fileProvider)

    def test() {
        given:
        def file = FileUtils.getFile("src/test/resources/cardBulkData.json")
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> file
        def cards = mapper.fromJsonListFile(file)
        when:
        updater.update()
        then:
        1 * repository.updateAll(cards)
    }

    def "repository shouldn't be updated if provided card list is null"() {
        given:
        def cards = null
        mapper.fromJsonListFile(_ as File) >> cards
        when:
        updater.update()
        then:
        0 * repository.updateAll(cards)
    }

    def "repository shouldn't be updated if card file is not recent one"() {

    }
}
