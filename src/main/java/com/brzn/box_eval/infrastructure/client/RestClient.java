package com.brzn.box_eval.infrastructure.client;

import com.brzn.box_eval.mtg_io_client.MtgIO;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.scryfall_client.Scryfall;
import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestClient implements Client {

    private final MtgIO mtgIO;
    private final Scryfall scryfall;

    @Override
    public List<CardSet> findCardSetsByName(Set<String> setNames) {
        return mtgIO.findCardSetsByName(setNames);
    }

    @Override
    public List<CardSet> findAllCardSets() {
        return mtgIO.findAllCardSets();
    }

    @Override
    public List<Card> getAllCards() {
        return scryfall.getAllCards(); //todo implementacja
    }
}
