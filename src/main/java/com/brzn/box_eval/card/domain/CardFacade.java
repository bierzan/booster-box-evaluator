package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.domain.dto.CardDto;
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

    public List<CardDto> findCardsReleasedAfter(LocalDate date) {
        return provider.findCardsReleasedAfter(date);
    }

    public List<CardDto> getAllCards() {
        return provider.getAll();
    }

}
