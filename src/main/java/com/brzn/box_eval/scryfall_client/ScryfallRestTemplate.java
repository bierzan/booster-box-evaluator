package com.brzn.box_eval.scryfall_client;

import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

class ScryfallRestTemplate {

    private RestTemplate restTemplate;

    public ScryfallRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Card> findCardsReleasedAfter(LocalDate releaseDate){
        return null;
    }
}

//todo https://api.scryfall.com/cards?q=date%3E2015-08-18