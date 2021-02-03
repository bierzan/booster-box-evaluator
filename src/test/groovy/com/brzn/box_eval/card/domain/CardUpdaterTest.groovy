package com.brzn.box_eval.card.domain


import com.brzn.box_eval.card.dto.CardDto
import com.brzn.box_eval.card.port.CardJsonFileProvider
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import spock.lang.Specification

import java.time.LocalDate

import static com.brzn.box_eval.card.domain.CardJsonTestUtil.getBulkDataReferenceFile
import static com.brzn.box_eval.card.domain.CardJsonTestUtil.mapJson2CardDtos

class CardUpdaterTest extends Specification {
    private CardJsonFileProvider fileProvider = Mock(CardJsonFileProvider)
    private CardMapper mapper = new CardMapper(new ObjectMapper())
    private CardRepository repository = Mock(CardRepository)
    private CardUpdater updater = new CardUpdater(repository, mapper, fileProvider)
    private File referenceBulkDataFile = getBulkDataReferenceFile()
    private List<CardDto> cardsFromJsonFile = mapJson2CardDtos(referenceBulkDataFile)

    def "should call update on card repository with recent cards"() {
        given:
        stubFindingLastCardUpdateDate(LocalDate.now())
        stubGettingJsonFIle(referenceBulkDataFile)
        when:
        updater.update()
        then:
        1 * repository.updateAll(cardsFromJsonFile)
    }

    def "should call update on card repository with recent cards without last update date info"() {
        given:
        stubFindingLastCardUpdateDate(null);
        stubGettingJsonFIle(referenceBulkDataFile)
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

    private void stubGettingJsonFIle(File file) {
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> file
    }

    private void stubFindingLastCardUpdateDate(LocalDate date) {
        repository.findLastCardUpdateDate() >> date
    }
}
