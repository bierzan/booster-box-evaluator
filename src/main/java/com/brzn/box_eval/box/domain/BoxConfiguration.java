package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.cache.CardProvider;
import com.brzn.box_eval.mtg_io_client.MtgIO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
class BoxConfiguration {

    private final CardProvider cardProvider; //todo nazwa klasy do zastanowienia
    private final MtgIO mtgIO;

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }

    @Bean
    BoxFacade boxFacade(BoxRepository repository) {
        BoxCreator creator = new BoxCreator();
        BoxFinder finder = new BoxFinder(cardProvider, mtgIO, creator);
        BoxCommand command = new BoxCommand(finder, repository);
        return new BoxFacade(command, repository);
    }
}

