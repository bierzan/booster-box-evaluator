package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import com.brzn.box_eval.card.port.CardJsonFileProvider
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import spock.lang.Specification

import java.time.LocalDate

import static com.brzn.box_eval.card.domain.CardJsonTestUtil.*

class CardFacadeTest extends Specification implements SampleCards {
    def repo = new InMemoryCardRepository()
    def query = new CardQuery(repo)
    def fileProvider = Mock(CardJsonFileProvider)
    def mapper = new CardMapper(new ObjectMapper())
    def updater = new CardUpdater(repo, mapper, fileProvider)
    def facade = new CardFacade(updater, query)
    def referenceBulkDataFile = getBulkDataReferenceFile()

    def cleanup() {
        deleteTemporaryJsons()
    }

    def "fill empty repo with recent cards"() {
        given: "Empty card repository"
        assert (repo.getAll().size() == 0)
        and: "JsonFile with recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> referenceBulkDataFile
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new cards"
        repoHasAllCardsFromFile(referenceBulkDataFile)
        and: "I see that all cards have current updateDate"
        allCardsInRepoHaveGivenUpdateDate(LocalDate.now())
    }

    def "update repo with recent cards"() {
        given: "Repo with lastWeekCard"
        repo.save(lastWeekCard.dto())
        and: "JsonFile with recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.lastUpdate as LocalDate) >> referenceBulkDataFile
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new and old cards"
        def newAndOldCards = mapJson2CardDtos(referenceBulkDataFile).append(lastWeekCard.dto())
        repoContainsOnlyCardsFromList(newAndOldCards)
        and: "I see all newly provided Cards have new updateDate changed"
        assertThatCardsHaveRecentUpdateDateInRepo(mapJson2CardDtos(referenceBulkDataFile))
    }

    def "update existing cards data"() {
        given: "Repo with lastWeekCard and veryOldCard"
        repo.save(lastWeekCard.dto())
        repo.save(veryOldCard.dto())
        and: "JsonFile with updated price of veryOldCard card"
        def cardWithNewPrice = updateCardWithNewPrice(veryOldCard, "666.66")
        def updatedCardsFile = getTempJsonCardsArrayFileFromCards("updatedCards", cardWithNewPrice)
        fileProvider.getCardsJsonFileReleasedAfter(_ as LocalDate) >> updatedCardsFile
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see that veryOldCard has new lastUpdate date and price"
        repoUpdatedDataForGivenCard(cardWithNewPrice)
    }

    def "don't update repo as no new cards were found"() {
        given: "Repo with lastWeekCard"
        repo.save(lastWeekCard.dto())
        and: "no recent cards data"
        fileProvider.getCardsJsonFileReleasedAfter(lastWeekCard.lastUpdate as LocalDate) >> null
        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with old card only"
        repoContainsOnlyCards(lastWeekCard.dto())
        then: "I see that cards update date hasn't changed"
        allCardsInRepoHaveGivenUpdateDate(lastWeekCard.dto().lastUpdate)
    }

    private Iterable<Card> repoUpdatedDataForGivenCard(cardWithNewPrice) {
        repo.getAll().each { card ->
            if (card.dto().uuid == cardWithNewPrice.uuid) {
                assert (card.lastUpdate == LocalDate.now())
                assert (card.dto().price == cardWithNewPrice.getPrice())
            }
        }
    }

    def repoHasAllCardsFromFile(File file) {
        def uuidsFromRepo = getAllUuidsFromRepoSorted()
        def uuidsFromFile = getAllUuidsFromCardsListSorted(mapJson2CardDtos(file))
        return uuidsFromRepo.containsAll(uuidsFromFile)
    }

    def getAllUuidsFromCardsListSorted(List<CardDto> cards) {
        cards.map({ card -> card.uuid })
                .sorted()
    }

    private List<String> getAllUuidsFromRepoSorted() {
        repo.getAll()
                .map({ card -> card.dto().uuid })
                .collect(List.collector())
                .sorted()
    }

    def assertThatCardsHaveRecentUpdateDateInRepo(List<CardDto> cards) {
        def cardsFromRepo = repo.getAll()
                .map({ card -> card.dto() })
                .filter({ card -> card.lastUpdate == LocalDate.now() })
                .map({ card -> card.uuid })
                .sorted()
        return cardsFromRepo == getAllUuidsFromCardsListSorted(cards)
    }

    def updateCardWithNewPrice(Card card, String newPrice) {
        def cardWithNewPrice = card.dto()
        cardWithNewPrice.setPrice(new BigDecimal(newPrice))
        return cardWithNewPrice
    }

    def repoContainsOnlyCards(CardDto... cards) {
        return repoContainsOnlyCardsFromList(List.of(cards))
    }

    def repoContainsOnlyCardsFromList(List<CardDto> cards) {
        return getAllUuidsFromRepoSorted() == getAllUuidsFromCardsListSorted(cards)
    }

    def allCardsInRepoHaveGivenUpdateDate(LocalDate date) {
        repo.getAll().each { card ->
            assert (card.getLastUpdate().isEqual(date))
        }
    }
}
