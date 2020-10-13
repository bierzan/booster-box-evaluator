package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.port.CardSetPropertiesProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class BoxConfiguration {

    private final CardSetPropertiesProvider cardSetPropertiesProvider;

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }

    @Bean
    BoxFacade boxFacade(BoxRepository repository) {
        BoosterSchemaCreator boosterSchemaCreator = new BoosterSchemaCreator();
        BoxCreator creator = new BoxCreator(boosterSchemaCreator);
        BoxFinder finder = new BoxFinder(creator, cardSetPropertiesProvider);
        BoxCommand command = new BoxCommand(finder, repository);
        return new BoxFacade(command);
    }
}

