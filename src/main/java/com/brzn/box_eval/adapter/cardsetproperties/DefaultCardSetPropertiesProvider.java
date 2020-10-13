package com.brzn.box_eval.adapter.cardsetproperties;

import com.brzn.box_eval.box.dto.CardSetProperties;
import com.brzn.box_eval.box.port.CardSetPropertiesProvider;
import com.brzn.box_eval.card.domain.CardFacade;
import com.brzn.box_eval.card.dto.CardDto;
import com.brzn.box_eval.infrastructure.client.Client;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class DefaultCardSetPropertiesProvider implements CardSetPropertiesProvider {
    private final CardFacade cardFacade;
    private final Client client;
    private final CardSetMapper mapper;

    @Override
    public List<CardSetProperties> findCardSetsReleasedAfter(LocalDate date) {
        Set<String> setNames = cardFacade.findCardsReleasedAfter(date)
                .map(CardDto::getSetName)
                .toSet();
        return client.findCardSetsByName(setNames)
                .map(mapper::convertToCardSetProperties);
    }

    @Override
    public List<CardSetProperties> findAllCardSets() {
        return client.findAllCardSets()
                .map(mapper::convertToCardSetProperties);
    }
}
