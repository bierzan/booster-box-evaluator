package com.brzn.box_eval.mtg_io_client.dto;


import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@EqualsAndHashCode
public class CardSetsArray {
    private List<CardSet> sets;

    public List<CardSet> getSets() {
        return Optional.ofNullable(sets).orElseGet(Collections::emptyList);
    }

    public CardSetsArray(List<CardSet> sets) {
        this.sets = sets;
    }

    public CardSetsArray() {
    }
}


