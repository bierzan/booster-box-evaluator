package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MtgIO {
    private final MtgIORestTemplate restTemplate;

    public List<CardSet> findCardSetsByName(Set<String> setNames) {
        SearchedCardSets searchedCardSets = new SearchedCardSets(setNames);
        if (searchedCardSets.hasValidSetNames()) {
            return List.ofAll(restTemplate.getCardSetsArrayByCardSetsNames(searchedCardSets.getNames()).getSets());
        }
        return List.empty();
    }

    public List<CardSet> findAllCardSets() {
        return List.ofAll(restTemplate.getCardSetsArrrayOfAllCardSets().getSets());
    }
}
