package com.brzn.box_eval.adapter.cardsetproperties;

import com.brzn.box_eval.box.port.CardSetPropertiesProvider;
import com.brzn.box_eval.card.domain.CardFacade;
import com.brzn.box_eval.infrastructure.client.Client;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class CardSetPropertiesProviderConfiguration {

    private final CardFacade facade;
    private final Client client;

    @Bean
    CardSetPropertiesProvider cardSetProvider(){
        return new DefaultCardSetPropertiesProvider(facade, client, new CardSetMapper());
    }
}
