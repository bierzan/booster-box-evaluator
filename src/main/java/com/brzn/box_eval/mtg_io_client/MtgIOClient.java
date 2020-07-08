package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class MtgIOClient {

    private final MtgIORestTemplate restTemplate;

    List<CardSet> findCardSetsByName(SearchedCardSets searchedCardSets) {
        return List.ofAll(restTemplate.getCardSetsArrayByCardSetsNames(searchedCardSets.getNames()).getSets());
    }
}
