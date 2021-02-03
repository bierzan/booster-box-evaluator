package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.port.CardJsonFileProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class CardConfiguration {

    private final CardJsonFileProvider cardJsonFileProvider;
    private final ObjectMapper mapper;

    @Bean
    CardFacade cardFacade() {
        return cardFacade(new InMemoryCardRepository());
    }

    CardFacade cardFacade(CardRepository repository) {
        CardUpdater updater = new CardUpdater(repository, new CardMapper(mapper), cardJsonFileProvider);
        CardQuery provider = new CardQuery(repository);
        return new CardFacade(updater, provider);
    }
}
