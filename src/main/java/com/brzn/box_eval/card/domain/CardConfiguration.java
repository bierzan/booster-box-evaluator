package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.infrastructure.client.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class CardConfiguration {

    private final Client client;
    private final ObjectMapper mapper;

    CardFacade cardFacade() {
        return cardFacade(new InMemoryCardRepository());
    }

    @Bean
    CardFacade cardFacade(CardRepository repository){
        CardUpdater updater = new CardUpdater(client, repository, mapper);
        CardProvider provider = new CardProvider(repository);
        return new CardFacade(updater, provider);
    }
}
