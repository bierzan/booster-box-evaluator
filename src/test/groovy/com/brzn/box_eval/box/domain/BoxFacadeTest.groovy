package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.CardSetProperties
import com.brzn.box_eval.box.interfaces.CardSetPropertiesProvider
import io.vavr.collection.List
import spock.lang.Specification

import java.time.LocalDate

class BoxFacadeTest extends Specification implements SampleBoxes, SampleCardSetProperties {

    def cardSetPropertiesProvider = Mock(CardSetPropertiesProvider)
    def boxCreator = new BoxCreator(new BoosterSchemaCreator())
    def repository = new InMemoryBoxRepository()

    def facade = createBoxFacade(repository)

    def "should fill empty Box inventory with recently released boxes"() {
        given: "Empty box repository"
        assert(repository.findAll().size() == 0)
        and: "Provided CardSetProperties"
        giveAllCardSetsProperties()

        when: "I invoke findNew"
        facade.findNew()

        then: "I see new Boxes in inventory"
        repository.findAll().contains(getBoxBySampleCardSetProperties(todayCardSet))
        repository.findAll().contains(getBoxBySampleCardSetProperties(lastWeekCardSet))
    }

    def "should update Box inventory with recently released boxes"() {
        given: "Repository with OldBox"
        fillRepositoryWithBoxes(oldBox);
        and: "Data about recently released card sets"
        giveCardSetProperties(todayCardSet, lastWeekCardSet)

        when: "I invoke findNew"
        facade.findNew()

        then: "I see new Boxes with OldBox in inventory"
        repository.findAll().contains(oldBox)
        repository.findAll().contains(getBoxBySampleCardSetProperties(todayCardSet))
        repository.findAll().contains(getBoxBySampleCardSetProperties(lastWeekCardSet))
        then: "I see that new Boxes were released after oldBox"
        repository.findAll().each { box ->
            oldBox.dto().releaseDate
                    .isBefore((box as Box).dto().releaseDate)
        }
    }

    def "shouldn't put anything into Box inventory"() {
        given: "inventory with recently released Box"
        fillRepositoryWithBoxes(todayBox);
        and: "Empty list of CardsetsProperties as there were no new releases"
        giveCardSetProperties(new CardSetProperties[0])

        when: "I invoke findNew"
        facade.findNew()

        then: "I that nothing was changed in inventory"
        repository.findAll().contains(todayBox)
        repository.findAll().size() == List.of(todayBox).size()
    }

    def giveAllCardSetsProperties() {
        cardSetPropertiesProvider.findAllCardSets() >> List.of(todayCardSet, lastWeekCardSet);
    }

    def giveCardSetProperties(CardSetProperties... properties) {
        cardSetPropertiesProvider.findCardSetsReleasedAfter(_ as LocalDate) >> List.of(properties)
    }

    def getBoxBySampleCardSetProperties(CardSetProperties properties) {
        return boxCreator.from(properties)
    }

    def createBoxFacade(BoxRepository repo) {
        BoxFinder finder = new BoxFinder(boxCreator, cardSetPropertiesProvider);
        BoxCommand command = new BoxCommand(finder, repo)
        return new BoxFacade(command, repo)
    }

    def fillRepositoryWithBoxes(Box... boxes) {
        repository.saveAll(List.of(boxes))
    }

}
