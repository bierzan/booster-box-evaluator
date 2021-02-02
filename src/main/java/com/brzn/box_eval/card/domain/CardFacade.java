package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CardFacade {
    private final CardUpdater updater;
    private final CardQuery provider;

    public void updateCardRepository() { //todo ustawic scheduler
        updater.update();
    }

    public List<CardDto> findCardsReleasedAfter(LocalDate date) {
        return provider.findCardsReleasedAfter(date);
    }
}
