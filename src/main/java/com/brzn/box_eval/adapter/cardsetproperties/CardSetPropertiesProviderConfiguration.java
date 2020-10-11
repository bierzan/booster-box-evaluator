package com.brzn.box_eval.adapter.cardsetproperties;

import com.brzn.box_eval.box.interfaces.CardSetPropertiesProvider;
import com.brzn.box_eval.card.domain.CardFacade;
import com.brzn.box_eval.infrastructure.client.Client;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class CardSetPropertiesProviderConfiguration {

    private final CardFacade facade;
    private final Client client;

    @Bean
    CardSetPropertiesProvider cardSetProvider(){
        return new DefaultCardSetPropertiesProvider(facade, client, new CardSetMapper());
    }
}
