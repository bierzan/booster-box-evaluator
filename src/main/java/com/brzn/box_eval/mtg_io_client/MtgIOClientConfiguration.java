package com.brzn.box_eval.mtg_io_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class MtgIOClientConfiguration {

    private RestTemplate restTemplate;

    MtgIOClient mtgIOClient() {
        return mtgIOClient(restTemplate);
    }

    @Bean
    MtgIOClient mtgIOClient(RestTemplate restTemplate) {
        return new MtgIOClient(restTemplate);
    }

}
