package com.brzn.box_eval.cache;

import com.brzn.box_eval.cache.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

public class CardProvider { //todo testy!!!!!!!!!!!
    private final CardCache cache;

    private CardProvider() {
        this.cache = new CardCache();
    }

    public CardProvider(CardCache cache) {
        this.cache = cache;
    }

    public List<Card> findCardsReleasedAfter(LocalDate date) {
        return cache.findCardsReleasedAfter(date); //todo obsluzyc nulla z cacha
        //todo test na zwrot pustej listy
        //todo date nie bedzie nullem
    }

    public List<Card> getAll() {
        return cache.getAll();
    }
}
