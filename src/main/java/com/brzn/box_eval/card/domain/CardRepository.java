package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import io.vavr.collection.List;

import java.time.LocalDate;

interface CardRepository {
    List<Card> findCardsReleasedAfter(LocalDate date);

    List<Card> getAll();

    void updateAll(List<CardDto> cards);

    LocalDate findLastCardUpdateDate();
}
