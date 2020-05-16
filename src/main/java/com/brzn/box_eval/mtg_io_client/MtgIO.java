package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MtgIO {
    private final MtgIOClient client;

    public List<CardSet> findCardSetsByName(Set<String> setNames) {
        return client.findCardSetsByName(setNames);
    }
    //todo w przypadku pustej listy nazw zwracac ma pusta liste setow - rest zwraca wszystkie

    public List<CardSet> findAllCardSets() {
        return List.empty();
    }
}
