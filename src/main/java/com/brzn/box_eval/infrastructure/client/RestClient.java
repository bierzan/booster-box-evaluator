package com.brzn.box_eval.infrastructure.client;

import com.brzn.box_eval.mtg_io_client.MtgIO;
import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.scryfall_client.Scryfall;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

import java.net.URL;
import java.time.LocalDate;

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
    public URL getUrlForCardDateUpdatedAfter(LocalDate lastUpdate) { //todo test na nulla
        return scryfall.getUrlForCardDateUpdatedAfter(lastUpdate);
    }
}
