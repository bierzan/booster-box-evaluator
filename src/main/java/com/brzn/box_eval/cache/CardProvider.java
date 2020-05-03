package com.brzn.box_eval.cache;

import com.brzn.box_eval.cache.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

public class CardProvider { //todo testy i konfiguracja
    private final CardCache cache;

    public CardProvider(CardCache cache) {
        this.cache = cache;
    }

    private CardProvider(){
        this.cache = null;
    }
    public List<Card> findCardsReleasedAfter(LocalDate date) {
        return cache.findCardsReleasedAfter(date); //todo obsluzyc nulla z cacha
        //todo test na zwrot pustej listy
    }
}
