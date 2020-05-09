package com.brzn.box_eval.cache;

import com.brzn.box_eval.cache.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

class CardCache {
    private List<Card> cardCache;

    public CardCache() {
        cardCache = List.empty();
    }

    List<Card> findCardsReleasedAfter(LocalDate date){
        return cardCache
                .filter(card -> card.getReleasedAt().isAfter(date))
                .toList();
    }

    List<Card> getAll(){
        return cardCache;
    }

    public void add(Card card){
        cardCache = cardCache.append(card);
    }
}
