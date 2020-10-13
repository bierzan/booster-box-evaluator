package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.port.CardJsonFileProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class CardConfiguration {

    private final CardJsonFileProvider cardJsonFileProvider;
    private final CardMapper cardMapper;

    CardFacade cardFacade() {
        return cardFacade(new InMemoryCardRepository());
    }

    @Bean
    CardFacade cardFacade(CardRepository repository) {
        CardUpdater updater = new CardUpdater(repository, cardMapper, cardJsonFileProvider);
        CardQuery provider = new CardQuery(repository);
        return new CardFacade(updater, provider);
    }
}
