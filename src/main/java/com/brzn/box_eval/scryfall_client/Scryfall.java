package com.brzn.box_eval.scryfall_client;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Scryfall {
    private final ScryfallRestTemplate restTemplate;
    public List<Card> getCards() { //todo testy i implementacja
        return restTemplate.getAllCards();
    }
}
