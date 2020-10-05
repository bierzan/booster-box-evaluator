package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.card.domain.CardFacade;
import com.brzn.box_eval.infrastructure.client.Client;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class BoxConfiguration {

    private final CardFacade cardFacade;
    private final Client client;

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }

    @Bean
    BoxFacade boxFacade(BoxRepository repository) {
        BoosterSchemaCreator boosterSchemaCreator = new BoosterSchemaCreator();
        BoxCreator creator = new BoxCreator(boosterSchemaCreator);
        BoxFinder finder = new BoxFinder(cardFacade, client , creator);
        BoxCommand command = new BoxCommand(finder, repository);
        return new BoxFacade(command, repository);
    }
}

