package com.brzn.box_eval.card.domain


import io.vavr.collection.List
import nl.altindag.log.LogCaptor
import spock.lang.Specification

import java.time.LocalDate

class CardProviderTest extends Specification implements SampleCards {
    def repo = new InMemoryCardRepository()
    def provider = new CardProvider(repo)
    def logCaptor = LogCaptor.forClass(CardProvider.class);

    def "should find cards released after given date"() {
        given: "Repo with lastWeekCard and todayCard"
        repo.updateAll(List.of(lastWeekCard, todayCard))
        and: "Very old date I want to use to look for cards"
        def date = LocalDate.MIN

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get lastWeekCard and todayCard from repo"
        cards.contains(lastWeekCard)
        cards.contains(todayCard)
        and: "No other cards returned from repo"
        cards.size() == 2
    }

    def "should return empty List as no cards were released after given date"() {
        given: "Repo with todayCard"
        repo.updateAll(List.of(todayCard))
        and: "Todays date I want to use to look for cards"
        def date = LocalDate.now()

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get empty list"
        cards.isEmpty()
    }

    def "should return empty List when given date is null"() {
        given: "Repo with todayCard"
        repo.updateAll(List.of(todayCard))

        when: "I invoke findCardsReleasedAfter with null as date"
        def cards = provider.findCardsReleasedAfter(null)

        then: "I get empty list"
        cards.isEmpty()
        and: "I see log warning me about passing null value"
        logCaptor.getLogs("warn").contains("date can't be null")
    }

    def "should return every element from repo"() {
        given: "Repo with todayCard"
        repo.updateAll(List.of(lastWeekCard, todayCard))

        when: "I invoke getAll"
        def cards = provider.getAll()

        then: "I get list of all cards"
        cards.sort() == List.of(todayCard, lastWeekCard).sort()
    }
}
