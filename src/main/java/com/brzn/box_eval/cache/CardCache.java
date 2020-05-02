package com.brzn.box_eval.cache;

import com.brzn.box_eval.cache.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

class CardCache {
    private final List<Card> cardCache;

    public CardCache() {
        cardCache = List.empty();
    }

    List<Card> findCardsReleasedAfter(LocalDate date){
        return cardCache
                .filter(card -> card.getReleasedAt().isAfter(date))
                .toList();
    }
}
