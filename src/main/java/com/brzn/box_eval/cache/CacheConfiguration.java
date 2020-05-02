package com.brzn.box_eval.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CacheConfiguration {

    @Bean
    CardProvider cardProvider(){
        return new CardProvider(new CardCache());
    }
}
