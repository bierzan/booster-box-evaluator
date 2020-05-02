package com.brzn.box_eval.box.domain

import com.brzn.box_eval.cache.CardProvider
import com.brzn.box_eval.mtg_io_client.MtgIO
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import java.time.LocalDate

class BoxFacadeTest extends Specification implements SampleBoxes {

    private CardProvider cardProvider = Mock(CardProvider)
    private final MtgIO mtgIO = Mock(MtgIO);

    private BoxFacade facade
    // todo wydzielic do ustawiacza testow

    def "should update Box inventory with recently released boxes"() {
        given: "inventory with OldBox and data from REST clients about new releases"
        InMemoryBoxRepository repository = createRepositoryWithBoxes(oldBox);
        facade = createFacadeWithGivenRepository(repository)
        cardProvider.findCardsReleasedAfter(_ as LocalDate) >> List.empty();
        mtgIO.findCardSetsByName(_ as Set<String>) >> List.empty()
        when: "I invoke findNew"
        facade.findNew()
        then: "I see new Boxes in inventory"
        repository.findAll().size() > [oldBox].size()
        then: "I see that new Boxes were released after oldBox"
        repository.findAll().each { box ->
            oldBox.getReleaseDate().isBefore(box.releaseDate)
        }
        //todo findAll do implementacji
    }

    def createFacadeWithGivenRepository(BoxRepository repo) {
        BoxCreator creator = new BoxCreator()
        BoxFinder finder = new BoxFinder(cardProvider, mtgIO, creator);
        BoxCommand command = new BoxCommand(creator, finder, repo)
        return new BoxFacade(command, repo)
    }

    def createRepositoryWithBoxes(Box... boxes) {
        BoxRepository repo = new InMemoryBoxRepository();
        boxes.each {
            repo.save(it)
        }
        return repo;
    }

}
