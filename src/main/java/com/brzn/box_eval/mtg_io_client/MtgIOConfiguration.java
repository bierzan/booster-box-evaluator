package com.brzn.box_eval.mtg_io_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class MtgIOConfiguration {

    private RestTemplate restTemplate;

    @Bean
    MtgIO mtgIO() {
        MtgIOClient client = new MtgIOClient();
        return new MtgIO(client);
    }


    //todo prywtny konstruktor jest przykryty kiedy dodajemy wieloargumentowy

}
