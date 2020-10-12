package com.brzn.box_eval.card.domain

import com.brzn.box_eval.card.domain.dto.CardDto
import io.vavr.collection.List
import nl.altindag.log.LogCaptor
import spock.lang.Specification

import java.time.LocalDate

class SetNamesProviderTest extends Specification implements SampleCards {
    def repo = new InMemoryCardRepository()
    def provider = new CardProvider(repo)
    def logCaptor = LogCaptor.forClass(CardProvider.class);

    def "should find cards released after given date"() {
        given: "Repo with lastWeekCard and todayCard"
        putCardsInRepo(lastWeekCard, todayCard)
        and: "Very old date I want to use to look for cards"
        def date = LocalDate.MIN

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get lastWeekCard and todayCard from repo"
        cards.contains(lastWeekCard.dto())
        cards.contains(todayCard.dto())
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
        def cards = provider.findCardsReleasedAfter(null)

        then: "I get empty list"
        cards.isEmpty()
        and: "I see log warning me about passing null value"
        logCaptor.getLogs("warn").contains("date can't be null")
    }

    def "should return every element from repo"() {
        given: "Repo with todayCard"
        putCardsInRepo(todayCard, lastWeekCard)

        when: "I invoke getAll"
        def cards = provider.getAll()

        then: "I get list of all cards"
        cards.sort() == List.of(todayCard.dto(), lastWeekCard.dto()).sort()
    }

    def putCardsInRepo(Card... cards) {
        List<CardDto> dtos = List.empty()
        cards.each {dtos = dtos.append(it.dto())}
        repo.updateAll(dtos)
    }

    def putCardsInRepo(Card... cards) {
        def cardDtos = Arrays.asList(cards).stream()
                .map({ card -> card.dto() })
                .collect(List.collector())
                .getOrElse({ -> List.empty() })
        repo.updateAll(List.of(cardDtos))
    }
}
