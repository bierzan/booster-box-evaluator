package com.brzn.box_eval.mtg_io_client;

import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray;
import io.vavr.collection.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
class MtgIORestTemplate {
    private static final String GET_FOR_CARDSETS_BY_NAMES = "https://api.magicthegathering.io/v1/sets?name=";
    private static final String GET_FOR_ALL_CARDSETS = "https://api.magicthegathering.io/v1/sets";

    private final RestTemplate restTemplate;

    public CardSetsArray getCardSetsArrayByCardSetsNames(Set<String> names) {
        String url = GET_FOR_CARDSETS_BY_NAMES + names.collect(Collectors.joining("|"));
        return getCardSetsArray(url);
    }

    public CardSetsArray getCardSetsArrrayOfAllCardSets() {
        CardSetsArray cardSetsArray = getCardSetsArray(GET_FOR_ALL_CARDSETS);
        if(cardSetsArray.getSets().isEmpty()){
            log.info(String.format("%s answered with empty list", GET_FOR_ALL_CARDSETS));
        }
        return cardSetsArray;
    }

    private CardSetsArray getCardSetsArray(String url) {
        return restTemplate.getForObject(url, CardSetsArray.class);
    }
}
