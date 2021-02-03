package com.brzn.box_eval.box.port;

import com.brzn.box_eval.box.dto.CardSetProperties;
import io.vavr.collection.List;

import java.time.LocalDate;

public interface CardSetPropertiesProvider {
    List<CardSetProperties> findCardSetsReleasedAfter(LocalDate date);

    List<CardSetProperties> findAllCardSets();
}
