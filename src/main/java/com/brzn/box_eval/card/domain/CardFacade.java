package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CardFacade {
    private final CardUpdater updater;
    private final CardProvider provider;

    public void updateCardRepository() {
        updater.update();
    }

    public List<Card> findCardsReleasedAfter(LocalDate date) {
        return provider.findCardsReleasedAfter(date);
    }

    public List<Card> getAllCards() {
        return provider.getAll();
    }

}
