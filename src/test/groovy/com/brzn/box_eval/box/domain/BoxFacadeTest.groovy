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

    def "should fill empty Box inventory with recently released boxes"() {
        given: "Empty inventory"
        InMemoryBoxRepository repository = new InMemoryBoxRepository()
        facade = createFacadeWithGivenRepository(repository)
        and: "Data about all Cards"
        cardProvider.getAll() >> List.of(todayCard, lastWeekCard)
        and: "CardSets containing provided cards"
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.of(todaySet, lastWeekSet)

        when: "I invoke findNew"
        facade.findNew()

        then: "I see new Boxes in inventory"
        repository.findAll().contains(boxFromCardSet(todaySet))
        repository.findAll().contains(boxFromCardSet(lastWeekSet))
    }

    def "should update Box inventory with recently released boxes"() {
        given: "inventory with OldBox"
        InMemoryBoxRepository repository = createRepositoryWithBoxes(oldBox);
        facade = createFacadeWithGivenRepository(repository)
        and: "Data about recently released Card"
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.of(todayCard, lastWeekCard)
        and: "CardSets containing provided cards"
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

    def "shouldn't put anything into Box inventory"() {
        given: "inventory with recently released Box"
        InMemoryBoxRepository repository = createRepositoryWithBoxes(todayBox);
        facade = createFacadeWithGivenRepository(repository)
        and: "Data from REST clients confirming no new releases"
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.empty()
        and: "Empty list of Cardsets as there were no new releases"
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.empty() //todo implementacja zapewniajaca zwrot pustej listy

        when: "I invoke findNew"
        facade.findNew()

        then: "I that nothing was changed in inventory"
        repository.findAll().contains(todayBox)
        repository.findAll().size() == List.of(todayBox).size()
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
