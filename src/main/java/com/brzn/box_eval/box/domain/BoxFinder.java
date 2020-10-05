package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.card.domain.CardFacade;
import com.brzn.box_eval.infrastructure.client.Client;
import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
class BoxFinder {
    private final CardFacade cardFacade;
    private final Client client;
    private final BoxCreator creator;

    public List<Box> findBoxesReleasedAfter(LocalDate date) {
        Set<String> setNames = cardFacade.findCardsReleasedAfter(date)
                .map(Card::getSetName)
                .toSet();
        return client.findCardSetsByName(setNames)
                .map(creator::from)
                .toList();
    }

    public List<Box> findAllBoxes() {
        return client.findAllCardSets()
                .map(creator::from)
                .toList();
    }

}
