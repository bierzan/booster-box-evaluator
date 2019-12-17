package com.brzn.box_eval.box.domain

import com.brzn.box_eval.mtgIOclient.domain.SampleSets
import com.brzn.box_eval.mtg_io_client.domain.MtgIOClient
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.domain.ScryfallClient
import io.vavr.collection.List
import io.vavr.control.Option
import spock.lang.Specification

import java.time.LocalDate

class BoxServiceTest extends Specification implements SampleBoxes, SampleSets, SampleCards {
    def creator = new BoxCreator()
    def repository = Mock(BoxRepository)
    def mtgIOClient = Mock(MtgIOClient)
    def scryfallClient = Mock(ScryfallClient)
    def service = new BoxService(repository, creator, mtgIOClient, scryfallClient);

    def "searchNew should return new Boxes of recently released sets"() {
        given: 'Inventory with oldBox and recent sets data provided by external MtG websites'
        repository.findLast() >> Option.of(creator.from(oldBox));
        scryfallClient.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard, lastWeekCard);
        mtgIOClient.findSetsByCodes(_ as List<String>) >> List.of(todaySet, lastWeekSet)

        when: 'I look for new boxes'
        def newBoxes = service.searchNew()

        then: 'I get boxes released after oldBox'
        newBoxes.each { assert it.releaseDate > oldBox.releaseDate }
    }
}
//todo wydzielenie odpowiedzialnosci update do nowej klasy
//todo Box creator
//todo sprawdzenie zeby lista była pusta przy aktualnych danych
//todo search to nie to samo co search and get
//todo warunki na puste listy z API
//todo przy produkcji boxa z cardsetu mapowanie boosterow
//todo okreslenie quantity na boxie po jego typie
//todo testowanie mapowania API

//todo klasa testowa do refaktoryzacji i wydzielania