package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

class MtgIOClient {

    public static final String GET_FOR_CARDSETS_BY_NAMES = "https://api.magicthegathering.io/v1/sets?name="; //todo private
    private final RestTemplate restTemplate;

    public MtgIOClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    List<CardSet> findCardSetsByName(Set<String> setNames) {

        String setNamesAsSingleString = setNames.collect(Collectors.joining("|"));
        String url = GET_FOR_CARDSETS_BY_NAMES + setNamesAsSingleString;
        return Option.of(restTemplate.getForObject(url, CardSetsArray.class)) //todo test jednostkowy na odpowiedz samego serwera MockRestServiceServer - wydzielenie restemplate do osobnego serwisu
                .map(CardSetsArray::getSets)
                .map(List::ofAll)
                .getOrElse(List.empty());
    }
}
