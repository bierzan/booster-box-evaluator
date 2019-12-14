package com.brzn.bboxeval.box.domain

import com.brzn.bboxeval.mtgIOClient.domain.MtgIOClient
import com.brzn.bboxeval.mtgIOClient.dto.CardSet
import com.brzn.bboxeval.scryfallClient.domain.ScryfallClient
import io.vavr.collection.List
import org.assertj.core.util.Lists
import spock.lang.Specification

import java.time.LocalDate

class BoxServiceTest extends Specification implements SampleBoxes {
    def repository = Mock(BoxRepository);
    def mtgIOClient = Mock(MtgIOClient);
    def scryfallClient = Mock(ScryfallClient);
    def service = new BoxService(repository, mtgIOClient, scryfallClient);

    def "SearchNew"() {
        given: 'Inventory with very old Box'
        repository.findLast() >> Box.builder().releaseDate(LocalDate.MIN).build();
        scryfallClient.findCardsReleasedAfter(_) >> List.empty();
        mtgIOClient.findSetsByCodes(_) >> List.of(
                CardSet.builder()
                .releaseDate(LocalDate.now())
                .build(),
                CardSet.builder()
                .releaseDate(LocalDate.now())
                .build()
        );
        when: 'I look for new boxes'
        def newBoxes = service.searchNew()
        then: 'I get boxes released after oldBox'
        newBoxes.each { assert it.releaseDate > oldBox.releaseDate }
    }
}
//todo zwracanie konkretnej listy w kliencie mtgio
//todo zwracanie konkretnej listy w kliencie scryfall
//todo cala metoda searchBoxesReleasedAfter do streama?
//todo wydzielenie odpowiedzialnosci update do nowej klasy
//todo

//todo klasa testowa do refaktoryzacji i wydzielania