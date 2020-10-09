package com.brzn.box_eval.box.domain

import com.brzn.box_eval.card.domain.Card
import com.brzn.box_eval.card.domain.SampleCards
import com.brzn.box_eval.card.domain.CardFacade
import com.brzn.box_eval.card.domain.dto.CardDto
import com.brzn.box_eval.infrastructure.client.RestClient
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetType
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import java.time.LocalDate

class BoxFacadeTest extends Specification implements SampleBoxes, SampleCards {

    def cardFacade = Mock(CardFacade)
    def client = Mock(RestClient);
    def boxCreator = new BoxCreator(new BoosterSchemaCreator())
    def repository = new InMemoryBoxRepository()

    def facade = createBoxFacade(repository)

    def "should fill empty Box inventory with recently released boxes"() {
        given: "Empty box repository"
        assert(repository.findAll().size() == 0)
        and: "CardSets provided by client"
        giveAllCardSets()

        when: "I invoke findNew"
        facade.findNew()

        then: "I see new Boxes in inventory"
        repository.findAll().contains(getBoxBySampleCard(todayCard))
        repository.findAll().contains(getBoxBySampleCard(lastWeekCard))
    }

    def "should update Box inventory with recently released boxes"() {
        given: "Repository with OldBox"
        fillRepositoryWithBoxes(oldBox);
        and: "Data about recently released Card"
        giveRecentlyReleasedCards(todayCard, lastWeekCard)
        and: "CardSets containing provided cards"
        giveCardSetsByCards(todayCard, lastWeekCard)

        when: "I invoke findNew"
        facade.findNew()

        then: "I see new Boxes with OldBox in inventory"
        repository.findAll().contains(oldBox)
        repository.findAll().contains(getBoxBySampleCard(todayCard))
        repository.findAll().contains(getBoxBySampleCard(lastWeekCard))
        then: "I see that new Boxes were released after oldBox"
        repository.findAll().each { box ->
            oldBox.dto().releaseDate
                    .isBefore((box as Box).dto().releaseDate)
        }
    }

    def "shouldn't put anything into Box inventory"() {
        given: "inventory with recently released Box"
        fillRepositoryWithBoxes(todayBox);
        and: "Data from REST clients confirming no new releases"
        giveRecentlyReleasedCards(new CardDto[0])
        and: "Empty list of Cardsets as there were no new releases"
        giveCardSetsByCards(new CardDto[0])

        when: "I invoke findNew"
        facade.findNew()

        then: "I that nothing was changed in inventory"
        repository.findAll().contains(todayBox)
        repository.findAll().size() == List.of(todayBox).size()
    }

    def giveAllCardSets() {
        client.findAllCardSets() >> giveCardSetListByCards(todayCard, lastWeekCard)
    }

    def giveCardSetsByCards(Card... cards) {
        client.findCardSetsByName(_ as Set<String>) >> giveCardSetListByCards(cards)
    }

    def giveCardSetListByCards(Card ...cards) {
        def cardSets = List.empty();
        cards.each {cardSets = cardSets.append(cardSetFrom(it.dto()))}
        return cardSets
    }

    def getBoxBySampleCard(Card card) {
        return boxCreator.from(cardSetFrom(card.dto))
    }

    def giveRecentlyReleasedCards(Card... cards) {
        cardFacade.findCardsReleasedAfter(_ as LocalDate) >> List.of(cards)
    }

    def createBoxFacade(BoxRepository repo) {
        BoxFinder finder = new BoxFinder(cardFacade, client, boxCreator);
        BoxCommand command = new BoxCommand(finder, repo)
        return new BoxFacade(command, repo)
    }

    def fillRepositoryWithBoxes(Box... boxes) {
        repository.saveAll(List.of(boxes))
    }

    def cardSetFrom(CardDto card) {
        return CardSet.builder()
                .releaseDate(card.releasedAt)
                .name(card.setName)
                .code(card.setCode)
                .type(CardSetType.EXPANSION)
                .block("")
                .build()
    }
}
