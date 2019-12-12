package com.brzn.bboxeval.box.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoxConfiguration {

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }


    @Bean
    BoxFacade boxFacade(BoxRepository repository) {
        BoxCreator creator = new BoxCreator();
        BoxService service = new BoxService(repository);
        return new BoxFacade(repository, creator, service);
    }
}

