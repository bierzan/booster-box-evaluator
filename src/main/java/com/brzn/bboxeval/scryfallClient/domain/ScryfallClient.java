package com.brzn.bboxeval.scryfallClient.domain;

import com.brzn.bboxeval.scryfallClient.dto.Card;
import io.vavr.collection.List;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class ScryfallClient {

    private RestTemplate restTemplate;

    public ScryfallClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Card> findCardsReleasedAfter(LocalDate releaseDate){
        return null;
    }
}

//todo https://api.scryfall.com/cards?q=date%3E2015-08-18