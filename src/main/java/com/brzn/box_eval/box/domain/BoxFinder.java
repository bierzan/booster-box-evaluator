package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.port.CardSetPropertiesProvider;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
class BoxFinder {
    private final BoxCreator creator;
    private final CardSetPropertiesProvider cardSetPropertiesProvider;

    public List<Box> findBoxesReleasedAfter(LocalDate date) {
        return cardSetPropertiesProvider.findCardSetsReleasedAfter(date)
                .map(creator::from)
                .toList();
    }

    public List<Box> findAllBoxes() {
        return cardSetPropertiesProvider.findAllCardSets()
                .map(creator::from)
                .toList();
    }

}
