package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

interface CardRepository {
    List<Card> findCardsReleasedAfter(LocalDate date);

    List<Card> getAll();

    void replaceContent(List<Card> cards);

    boolean isOlderThan(LocalDateTime updatedAt);
}
