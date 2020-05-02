package com.brzn.box_eval.box.domain

import com.brzn.box_eval.mtgIOclient.domain.SampleSets
import com.brzn.box_eval.mtg_io_client.MtgIOClient
import com.brzn.box_eval.scryfall_client.ScryfallClient
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.dto.Card
import io.vavr.collection.List
import io.vavr.control.Option
import spock.lang.Specification

import java.time.LocalDate

class SearchForNewTest extends Specification implements SampleBoxes, SampleSets, SampleCards {
    def creator = new BoxCreator()
    def repository = Mock(BoxRepository)
    def mtgIOClient = Mock(MtgIOClient)
    def scryfallClient = Mock(ScryfallClient)
    def service = new BoxService(repository, creator, mtgIOClient, scryfallClient);

    def "searchForhNew with oldBox in inventory"() {
        given: 'Inventory with oldBox and recent sets data provided by external MtG websites'
        repository.findLast() >> Option.of(creator.from(oldBoxDto));
        scryfallClient.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard, lastWeekCard);
        mtgIOClient.findSetsByCodes(_ as List<String>) >> List.of(todaySet, lastWeekSet)

        when: 'I look for new boxes'
        def newBoxes = service.searchForNew()

        then: 'I get boxes released after oldBox'
        newBoxes.each { assert it.releaseDate > oldBoxDto.releaseDate }
    }

    def "searchForNew with up-to-date inventory"() {
        given: "Inventory with up-to-date boxes"
        repository.findLast() >> Option.of(creator.from(todaysBox));
        scryfallClient.findCardsReleasedAfter(_ as LocalDate) >> List.empty();
        mtgIOClient.findSetsByCodes(_ as List<String>) >> List.empty()

        when: 'I look for new boxes'
        def newBoxes = service.searchForNew();

        then: 'I get empty list'
        newBoxes.isEmpty();
    }

    def "searchForNew with empty inventory"() {
        given: "Empty inventory"
        repository.findLast() >> Option.none();
        scryfallClient.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard, lastWeekCard);
        mtgIOClient.findSetsByCodes(_ as List<String>) >> List.of(todaySet, lastWeekSet)

        when: 'I look for new boxes'
        def newBoxes = service.searchForNew();

        then: 'I get list of today and lastWeek Boxes'
        newBoxes.each { assert it.cardSetName == todaysBox.cardSetName || it.cardSetName == lastWeekBox.cardSetName }
    }

    @Override
    Card getTodayCard() {
        return SampleCards.super.getTodayCard()
    }

    @Override
    void setTodayCard(Card todayCard) {
        SampleCards.super.setTodayCard(todayCard)
    }

    @Override
    Card getLastWeekCard() {
        return SampleCards.super.getLastWeekCard()
    }

    @Override
    void setLastWeekCard(Card lastWeekCard) {
        SampleCards.super.setLastWeekCard(lastWeekCard)
    }
}
//todo !! Box creator test w kontekscie boostera
//todo okreslenie quantity na boxie po jego typie
//todo testowanie mapowania API
//todo ograniczyc test do sprawdzania jednej metody

//todo klasa testowa do refaktoryzacji i wydzielania