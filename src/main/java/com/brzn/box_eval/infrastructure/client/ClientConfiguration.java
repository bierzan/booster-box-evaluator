package com.brzn.box_eval.infrastructure.client;

import com.brzn.box_eval.mtg_io_client.MtgIO;
import com.brzn.box_eval.scryfall_client.Scryfall;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class ClientConfiguration {

    private final MtgIO mtgIO;
    private final Scryfall scryfall;

    @Bean
    Client client() {
        return new RestClient(mtgIO, scryfall);
    }
}
