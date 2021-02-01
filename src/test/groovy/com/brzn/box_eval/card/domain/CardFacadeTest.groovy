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
    def fromJsonFileCards = CardsFromResources.readJson2CardDtos(file)

    def "fill empty repo with recent cards"() {
        given: "Empty card repository"
        assert (repo.getAll().size() == 0)
        and: "JsonFile with recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> file
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new cards"
        repo.getAll().map({ card -> card.dto().uuid }).sort() == fromJsonFileCards.map({ card -> card.uuid }).sort()
    }

    def "update repo with recent cards"() {
        given: "Repo with lastWeekCard"
        repo.save(lastWeekCard.dto())
        and: "JsonFile with recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.lastUpdate as LocalDate) >> file
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new and old cards"
        repoContainsNewCardsAndOldCards()
        then: "I see all Cards updateDate has changed"
        repo.getAll().each { assert (it.getLastUpdate().isEqual(LocalDate.now())) }
    }

    def "don't update repo as no new cards were found"() {
        given: "Repo with lastWeekCard"
        repo.save(lastWeekCard.dto())
        and: "no recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.lastUpdate as LocalDate) >> null
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with old card only"
        repoContainsOnlyCards(lastWeekCard)
        then: "I see that cards update date hasn't changed"
        repo.getAll().each { assert (it.getLastUpdate().isEqual(lastWeekCard.lastUpdate)) }
    }

    def "don't update repo with new information about lastWeekCard"() {
        given: "Repo with lastWeekCard"
        repo.save(lastWeekCard.dto())
        and: "no recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.lastUpdate as LocalDate) >> null
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with old card only"
        repoContainsOnlyCards(lastWeekCard)
        then: "I see that cards update date hasn't changed"
        repo.getAll().filter({ c -> c.refersTo(lastWeekCard.dto()) }).first().getLastUpdate() == LocalDate.now()
    }

    def repoContainsOnlyCards(Card... cards) {
        def cardUuidsFromRepo = repo.getAll()
                .map({ card -> card.dto() })
                .map({ dto -> dto.getUuid() })
                .collect(List.collector())

        def expectedUuids = Arrays.stream(cards)
                .map({ card -> card.dto() })
                .map({ dto -> dto.uuid })
                .collect(List.collector())

        return cardUuidsFromRepo.containsAll(expectedUuids) &&
                expectedUuids.containsAll(cardUuidsFromRepo)
    }

    def repoContainsNewCardsAndOldCards() {
        def cardUuidsFromRepo = repo.getAll()
                .map({ card -> card.dto() })
                .map({ dto -> dto.getUuid() })
                .collect(List.collector())

        fromJsonFileCards = fromJsonFileCards.append(lastWeekCard.dto())

        def expectedUuids = fromJsonFileCards
                .map({ c -> c.uuid })
                .collect(List.collector())

        return cardUuidsFromRepo.containsAll(expectedUuids) &&
                expectedUuids.containsAll(cardUuidsFromRepo)
    }

}
