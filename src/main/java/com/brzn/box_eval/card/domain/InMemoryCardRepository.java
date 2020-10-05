package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

class InMemoryCardRepository implements CardRepository {
    private LocalDateTime updateDate;
    private List<Card> cardCache;

    public InMemoryCardRepository() {
        updateDate = LocalDateTime.MIN;
        cardCache = List.empty();
    }

    @Override
    public List<Card> findCardsReleasedAfter(LocalDate date){
        return cardCache
                .filter(card -> card.getReleasedAt().isAfter(date))
                .toList();
    }

    @Override
    public List<Card> getAll(){
        return cardCache;
    }

    @Override
    public void replaceContent(List<Card> cards){
        cardCache = cards;
        updateDate = LocalDateTime.now();
    }

    @Override
    public boolean isOlderThan(LocalDateTime updatedAt) { //todo test
        return updateDate.isBefore(updatedAt);
    }
}
