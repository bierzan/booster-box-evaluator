package com.brzn.box_eval.mtg_io_client;

import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
class SearchedCardSetNames {
    private final Set<String> cardSetNames;

    public boolean hasValidSetNames() {
        return Objects.nonNull(cardSetNames) && cardSetNames.nonEmpty();
    }
}
