package com.brzn.box_eval.scryfall_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class ScryfallConfiguration {

    @Bean
    Scryfall scryfall() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().set("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        });
        ScryfallRestTemplate scryfallRestTemplate = new ScryfallRestTemplate(restTemplate);
        return new Scryfall(scryfallRestTemplate);
    }

}
