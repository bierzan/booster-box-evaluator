package com.brzn.box_eval.infrastructure.client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import java.net.URL;
import java.time.LocalDate;

public interface Client {

    List<CardSet> findCardSetsByName(Set<String> setNames); //todo zamiast cardsetu hashmape?

    List<CardSet> findAllCardSets();

    URL getUrlForCardDateUpdatedAfter(LocalDate lastUpdate);
}
