package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.dto.CardDto
import io.vavr.collection.List
import nl.altindag.log.LogCaptor
import spock.lang.Specification

import java.time.LocalDate

class SetNamesProviderTest extends Specification implements SampleCards {
    def repo = new InMemoryCardRepository()
    def provider = new CardQuery(repo)
    def logCaptor = LogCaptor.forClass(CardQuery.class);
    def cards

    def "should find cards released after given date"() {
        given: "Repo with lastWeekCard and todayCard"
        putCardsInRepo(lastWeekCard, todayCard)
        and: "Very old date I want to use to look for cards"
        def date = LocalDate.MIN

        when: "I invoke findCardsReleasedAfter with given date"
        cards = provider.findCardsReleasedAfter(date)

        then: "I get lastWeekCard and todayCard from repo"
        assertThatCardsListContainsCard(lastWeekCard)
        assertThatCardsListContainsCard(todayCard)
        and: "No other cards returned from repo"
        cards.size() == 2
    }

    def "should return empty List as no cards were released after given date"() {
        given: "Repo with todayCard"
        putCardsInRepo(todayCard)
        and: "Todays date I want to use to look for cards"
        def date = LocalDate.now()

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get empty list"
        cards.isEmpty()
    }

    def "should return empty List when given date is null"() {
        given: "Repo with todayCard"
        putCardsInRepo(todayCard)

        when: "I invoke findCardsReleasedAfter with null as date"
        cards = provider.findCardsReleasedAfter(null)

        then: "I get empty list"
        cards.isEmpty()
        and: "I see log warning me about passing null value"
        logCaptor.getLogs("warn").contains("date can't be null")
    }

    def "should return every element from repo"() {
        given: "Repo with todayCard"
        putCardsInRepo(todayCard, lastWeekCard)

        when: "I invoke getAll"
        cards = provider.getAll()

        then: "I get list of all cards"
        assertThatCardsListContainsOnlyGivenCards(todayCard, lastWeekCard)
    }

    def putCardsInRepo(Card... cards) {
        List<CardDto> dtos = List.empty()
        cards.each { dtos = dtos.append(it.dto()) }
        repo.updateAll(dtos)
    }

    def assertThatCardsListContainsCard(Card searchedCard) {
        return cards.map({ card -> card.uuid })
                .contains(searchedCard.dto().uuid)
    }

    def assertThatCardsListContainsOnlyGivenCards(Card... searchedCards) {
        def searchedUUIDs = Arrays.stream(searchedCards)
                .map({ card -> card.dto().getUuid() })
                .collect(List.collector())

        return cards.map({ card -> card.uuid }).sort() == searchedUUIDs.sort()
    }
}
