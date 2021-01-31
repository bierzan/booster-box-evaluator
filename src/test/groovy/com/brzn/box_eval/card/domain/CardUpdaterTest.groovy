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
    def cardBulkDataFile = FileUtils.getFile("src/test/resources/cardBulkData.json")
    def cardsFromJsonFile = mapper.fromJsonListFile(cardBulkDataFile)

    def "should call update on card repository with recent cards"() {
        given:
        stubFindingLastCardUpdateDate(LocalDate.now())
        stubGettingJsonFIle(cardBulkDataFile)
        when:
        updater.update()
        then:
        1 * repository.updateAll(cardsFromJsonFile)
    }

    def "should call update on card repository with recent cards without last update date info"() {
        given:
        stubFindingLastCardUpdateDate(null);
        stubGettingJsonFIle(cardBulkDataFile)
        when:
        updater.update()
        then:
        1 * repository.updateAll(cardsFromJsonFile)
    }

    def "should not call update on card repository when no file is provided"() {
        given:
        stubFindingLastCardUpdateDate(LocalDate.now())
        stubGettingJsonFIle(null)
        when:
        updater.update()
        then:
        0 * repository.updateAll(_)
    }

    def stubGettingJsonFIle(File file) {
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> file
    }

    def stubFindingLastCardUpdateDate(LocalDate date) {
        repository.findLastCardUpdateDate() >> date
    }
}
