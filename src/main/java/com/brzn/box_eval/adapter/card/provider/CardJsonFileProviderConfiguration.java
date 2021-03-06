package com.brzn.box_eval.adapter.card.provider;

import com.brzn.box_eval.application.FileDownloader;
import com.brzn.box_eval.card.port.CardJsonFileProvider;
import com.brzn.box_eval.infrastructure.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class CardJsonFileProviderConfiguration {

    private final Client client;
    private final FileDownloader fileDownloader;

    @Bean
    CardJsonFileProvider cardJsonFileProvider(){
        return new DefaultCardJsonFileProvider(fileDownloader, client);
    }
}
