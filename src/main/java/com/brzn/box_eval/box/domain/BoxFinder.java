package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.cache.CardProvider;
import com.brzn.box_eval.cache.dto.Card;
import com.brzn.box_eval.mtg_io_client.MtgIO;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BoxFinder {
    private final CardProvider cardProvider;
    private final MtgIO mtgIO;
    private final BoxCreator creator;

    public List<Box> findBoxesReleasedAfter(LocalDate date) { //todo test
        Set<String> setNames = getNamesOfSetsReleasedAfter(date);

        return mtgIO.findCardSetsByName(setNames)
                .map(creator::from)
                .toList();
    }

    private Set<String> getNamesOfSetsReleasedAfter(LocalDate date) {
        return cardProvider.findCardsReleasedAfter(date)
                    .map(Card::getSetName)
                    .toSet();
    }
}
