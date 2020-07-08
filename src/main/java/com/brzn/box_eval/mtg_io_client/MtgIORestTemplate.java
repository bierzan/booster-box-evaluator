package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
class MtgIORestTemplate {
    private static final String GET_FOR_CARDSETS_BY_NAMES = "https://api.magicthegathering.io/v1/sets?name=";

    private final RestTemplate restTemplate;

    public CardSetsArray getCardSetsArrayByCardSetsNames(Set<String> names){
        String url = GET_FOR_CARDSETS_BY_NAMES + names.collect(Collectors.joining("|"));
        return Option.of(restTemplate.getForObject(url, CardSetsArray.class))
                .getOrElse(new CardSetsArray(Collections.emptyList())); //todo dodac logowanie
    }
}
