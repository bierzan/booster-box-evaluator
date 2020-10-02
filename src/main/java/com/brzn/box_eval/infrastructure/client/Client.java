package com.brzn.box_eval.infrastructure.client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import io.vavr.collection.Set;

public interface Client {

    List<CardSet> findCardSetsByName(Set<String> setNames);

    List<CardSet> findAllCardSets();

    List<Card> getAllCards();
}
