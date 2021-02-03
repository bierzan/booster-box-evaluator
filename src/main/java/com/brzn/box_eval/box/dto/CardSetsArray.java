package com.brzn.box_eval.box.dto;


import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@EqualsAndHashCode
public class CardSetsArray {
    private List<CardSetProperties> sets;

    public List<CardSetProperties> getSets() {
        return Optional.ofNullable(sets).orElseGet(Collections::emptyList);
    }

    public CardSetsArray(List<CardSetProperties> sets) {
        this.sets = sets;
    }

    public CardSetsArray() {
    }
}


