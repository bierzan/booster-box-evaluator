package com.brzn.bboxeval.mtgIOClient.domain;

import com.brzn.bboxeval.mtgIOClient.dto.CardSet;
import io.vavr.collection.List;
import org.springframework.web.client.RestTemplate;

public class MtgIOClient {

    private RestTemplate restTemplate;

    MtgIOClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CardSet> findSetsByCodes(List<String> setCodes){
        return null;
    }
}
