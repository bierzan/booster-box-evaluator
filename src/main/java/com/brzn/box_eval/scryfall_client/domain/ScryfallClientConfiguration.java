package com.brzn.box_eval.scryfall_client.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ScryfallClientConfiguration {

    private RestTemplate restTemplate;

    ScryfallClientConfiguration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    ScryfallClient scryfallClient() {
        return scryfallClient(restTemplate);
    }

    @Bean
    ScryfallClient scryfallClient(RestTemplate restTemplate) {
        return new ScryfallClient(restTemplate);
    }


}
