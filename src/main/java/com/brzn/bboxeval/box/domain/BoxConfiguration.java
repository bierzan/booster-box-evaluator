package com.brzn.bboxeval.box.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoxConfiguration {

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }


    @Bean
    BoxFacade boxFacade(BoxRepository boxRepository) {
        BoxCreator boxCreator = new BoxCreator();
        return new BoxFacade(boxRepository, boxCreator);
    }
}

