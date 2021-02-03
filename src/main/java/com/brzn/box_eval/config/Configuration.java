package com.brzn.box_eval.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
