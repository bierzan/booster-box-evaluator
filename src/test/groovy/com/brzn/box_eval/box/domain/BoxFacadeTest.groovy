package com.brzn.box_eval.box.domain

import com.brzn.box_eval.cache.CardProvider
import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.MtgIO
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.scryfall_client.domain.CachedCards
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import java.time.LocalDate

class BoxFacadeTest extends Specification implements SampleBoxes, SampleCardSets, CachedCards {

    private CardProvider cardProvider = Mock(CardProvider)
    private MtgIO mtgIO = Mock(MtgIO);
    private BoxCreator creator = new BoxCreator()

    private BoxFacade facade
    // todo wydzielic do ustawiacza testow

    def "should update Box inventory with recently released boxes"() {
        given: "inventory with OldBox and data from REST clients about new releases"
        InMemoryBoxRepository repository = createRepositoryWithBoxes(oldBox);
        facade = createFacadeWithGivenRepository(repository)
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard)
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.of(todaySet, lastWeekSet)
        when: "I invoke findNew"
        facade.findNew()
        then: "I see new Boxes with OldBox in inventory"
        repository.findAll().contains(oldBox)
        repository.findAll().contains(boxFromCardSet(todaySet))
        repository.findAll().contains(boxFromCardSet(lastWeekSet))
        then: "I see that new Boxes were released after oldBox"
        repository.findAll().each { box ->
            oldBox.getReleaseDate().isBefore(box.releaseDate)
        }
    }

    def createFacadeWithGivenRepository(BoxRepository repo) {
        BoxFinder finder = new BoxFinder(cardProvider, mtgIO, creator);
        BoxCommand command = new BoxCommand(finder, repo)
        return new BoxFacade(command, repo)
    }

    def createRepositoryWithBoxes(Box... boxes) {
        BoxRepository repo = new InMemoryBoxRepository();
        boxes.each {
            repo.save(it)
        }
        return repo;
    }

    def boxFromCardSet(CardSet cardSet) {
        return creator.from(cardSet)
    }
}
