package com.brzn.box_eval.cache

import com.brzn.box_eval.scryfall_client.domain.CachedCards
import spock.lang.Specification

import java.time.LocalDate

class CardProviderTest extends Specification implements CachedCards {
    CardCache cache = new CardCache()
    CardProvider provider = new CardProvider(cache)

    def "should find cards released after given date"(){
        given: "Cache with lastWeekCard and todayCard"
        cache.add(lastWeekCard)
        cache.add(todayCard)
        and: "Very old date I want to use to look for cards"
        def date = LocalDate.MIN

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get lastWeekCard and todayCard from cache"
        cards.contains(lastWeekCard)
        cards.contains(todayCard)
        and: "No other cards returned from cache"
        cards.size() == 2
    }

    def "should return empty List as no cards were released after given date"(){
        given: "Cache with todayCard"
        cache.add(todayCard)
        and: "Todays date I want to use to look for cards"
        def date = LocalDate.now()

        when: "I invoke findCardsReleasedAfter with given date"
        def cards = provider.findCardsReleasedAfter(date)

        then: "I get empty list"
        cards.isEmpty()
    }

    def "should return empty List when given date is null"(){
        given: "Cache with todayCard"
        cache.add(todayCard)

        when: "I invoke findCardsReleasedAfter with null as date"
        def cards = provider.findCardsReleasedAfter(null)

        then: "I get empty list"
        cards.isEmpty()
    }
}
