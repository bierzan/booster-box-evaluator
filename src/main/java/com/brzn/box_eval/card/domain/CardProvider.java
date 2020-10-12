package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.domain.dto.CardDto;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
@AllArgsConstructor
class CardProvider {
    private final CardRepository repo;

    public List<CardDto> findCardsReleasedAfter(LocalDate date) {
        return Option.of(date)
                .map(repo::findCardsReleasedAfter)
                .getOrElse(() -> {
                    log.warn("date can't be null");
                    return List.empty();
                })
                .map(Card::dto)
                .collect(List.collector());
    }

    public List<CardDto> getAll() {
        return repo.getAll()
                .map(Card::dto)
                .collect(List.collector());
    }
}
