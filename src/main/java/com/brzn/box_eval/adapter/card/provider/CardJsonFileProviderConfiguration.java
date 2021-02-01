package com.brzn.box_eval.adapter.card.provider;

import com.brzn.box_eval.card.port.CardJsonFileProvider;
import com.brzn.box_eval.infrastructure.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class CardJsonFileProviderConfiguration {

    private final Client client;

    @Bean
    CardJsonFileProvider cardJsonFileProvider(){
        return new DefaultCardJsonFileProvider(new FileDownloader(), client);
    }
}
