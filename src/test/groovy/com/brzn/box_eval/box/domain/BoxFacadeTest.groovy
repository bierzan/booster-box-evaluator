package com.brzn.box_eval.box.domain

import com.brzn.box_eval.cache.CachedCards
import com.brzn.box_eval.cache.CardProvider
import com.brzn.box_eval.cache.dto.Card
import com.brzn.box_eval.mtg_io_client.MtgIO
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import java.time.LocalDate

class BoxFacadeTest extends Specification implements SampleBoxes, CachedCards {

    CardProvider cardProvider = Mock(CardProvider)
    MtgIO mtgIO = Mock(MtgIO);
    BoxCreator boxCreator = new BoxCreator()
    BoxRepository repository

    def "should fill empty Box inventory with recently released boxes"() {
        given: "Empty inventory"
        repository = new InMemoryBoxRepository()
        and: "All CardSets"
        def todaySet = cardSetFrom(todayCard)
        def lastWeekSet = cardSetFrom(lastWeekCard)
        mtgIO.findAllCardSets() >> List.of(todaySet, lastWeekSet)

        when: "I invoke findNew"
        createBoxFacade(repository).findNew()

        then: "I see new Boxes in inventory"
        repository.findAll().contains(boxCreator.from(todaySet))
        repository.findAll().contains(boxCreator.from(lastWeekSet))
    }

    def "should update Box inventory with recently released boxes"() {
        given: "inventory with OldBox"
        repository = createRepositoryWithBoxes(oldBox);
        and: "Data about recently released Card"
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard)
        and: "CardSets containing provided cards"
        def todaySet = cardSetFrom(todayCard)
        def lastWeekSet = cardSetFrom(lastWeekCard)
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.of(todaySet, lastWeekSet)

        when: "I invoke findNew"
        createBoxFacade(repository).findNew()

        then: "I see new Boxes with OldBox in inventory"
        repository.findAll().contains(oldBox)
        repository.findAll().contains(boxCreator.from(todaySet))
        repository.findAll().contains(boxCreator.from(lastWeekSet))
        then: "I see that new Boxes were released after oldBox"
        repository.findAll().each { box ->
            oldBox.getReleaseDate().isBefore((box as Box).releaseDate)
        }
    }

    def "shouldn't put anything into Box inventory"() {
        given: "inventory with recently released Box"
        repository = createRepositoryWithBoxes(todayBox);
        and: "Data from REST clients confirming no new releases"
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.empty()
        and: "Empty list of Cardsets as there were no new releases"
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.empty() //todo implementacja zapewniajaca zwrot pustej listy

        when: "I invoke findNew"
        createBoxFacade(repository).findNew()

        then: "I that nothing was changed in inventory"
        repository.findAll().contains(todayBox)
        repository.findAll().size() == List.of(todayBox).size()
    }

    def createBoxFacade(BoxRepository repo) {
        BoxFinder finder = new BoxFinder(cardProvider, mtgIO, boxCreator);
        BoxCommand command = new BoxCommand(finder, repo)
        return new BoxFacade(command, repo)
    }

    def createRepositoryWithBoxes(Box... boxes) {
        BoxRepository repo = new InMemoryBoxRepository();
        boxes.each {
            repo.save(it as Box)
        }
        return repo;
    }

    def cardSetFrom(Card card) {
        return CardSet.builder()
                .releaseDate(card.releasedAt)
                .name(card.setName)
                .code(card.setCode)
                .type(CardSetType.EXPANSION)
                .block("")
                .build()
    }
}
