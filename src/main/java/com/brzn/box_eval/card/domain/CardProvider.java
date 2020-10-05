package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
class CardProvider {
    private final CardRepository repo;

    public List<Card> findCardsReleasedAfter(LocalDate date) {
        return Option.of(date)
                .map(repo::findCardsReleasedAfter)
                .getOrElse(() -> {
                    log.warn("date can't be null");
                    return List.empty();
                });
    }

    public List<Card> getAll() {
        return repo.getAll();
    }
}
