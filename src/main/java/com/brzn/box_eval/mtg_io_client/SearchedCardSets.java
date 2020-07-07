package com.brzn.box_eval.mtg_io_client;

import io.vavr.collection.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
class SearchedCardSets {
    private final Set<String> names;

    public boolean hasValidSetNames() {
        return Objects.nonNull(names) && names.nonEmpty();
    }
}
