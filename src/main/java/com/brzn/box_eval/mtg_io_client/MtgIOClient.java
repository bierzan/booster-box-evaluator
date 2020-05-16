package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSet;
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

class MtgIOClient {

    public static final String GET_FOR_CARDSETS_BY_NAMES = "https://api.magicthegathering.io/v1/sets?name=";
    private final RestTemplate restTemplate;

    public MtgIOClient() {
        this.restTemplate = new RestTemplate();
    }

    List<CardSet> findCardSetsByName(Set<String> setNames) {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });
        String setNamesAsSingleString = setNames.collect(Collectors.joining("|"));
        String url = GET_FOR_CARDSETS_BY_NAMES + setNamesAsSingleString;
        return Option.of(restTemplate.getForObject(url, CardSetsArray.class))
                .map(CardSetsArray::getSets)
                .map(List::ofAll)
                .getOrElse(List.empty());
    }
}
