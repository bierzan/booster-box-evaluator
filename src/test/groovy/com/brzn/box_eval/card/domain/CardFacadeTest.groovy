package com.brzn.box_eval.card.domain


import com.brzn.box_eval.card.port.CardJsonFileProvider
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import org.apache.commons.io.FileUtils
import spock.lang.Specification

import java.time.LocalDate

class CardFacadeTest extends Specification implements SampleCards {
    def repo = new InMemoryCardRepository()
    def query = new CardQuery(repo)
    def fileProvider = Mock(CardJsonFileProvider)
    def mapper = new CardMapper(new ObjectMapper())
    def updater = new CardUpdater(repo, mapper, fileProvider)
    def facade = new CardFacade(updater, query)
    def file = FileUtils.getFile("src/test/resources/cardBulkData.json")

    def "update acceptance test"() {
        given: "Repo updated with some cards last week"
        repo.updateAll(List.of(lastWeekCard.dto()))
        repo.findLastCardUpdateDate().isEqual(LocalDate.now().minusWeeks(1))
        and: "JsonFile with recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.getLastUpdate()) >> file
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new and old cards"
        repo.getAll().contains(todayCard)
        repo.getAll().contains(lastWeekCard)
        repo.getAll().size() == 2
        then: "I see all Cards updateDate has changed"
        repo.getAll().each {it.getLastUpdate().isEqual(LocalDate.now())}
    }
}
