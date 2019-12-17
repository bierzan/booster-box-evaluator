package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.mtg_io_client.domain.MtgIOClient;
import com.brzn.box_eval.scryfall_client.domain.ScryfallClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BoxConfiguration {

    private MtgIOClient mtgIOClient;
    private ScryfallClient scryfallClient;

    BoxConfiguration(MtgIOClient mtgIOClient, ScryfallClient scryfallClient) {
        this.mtgIOClient = mtgIOClient;
        this.scryfallClient = scryfallClient;
    }

    BoxFacade boxFacade() {
        return boxFacade(new InMemoryBoxRepository());
    }


    @Bean
    BoxFacade boxFacade(BoxRepository repository) {
        BoxCreator creator = new BoxCreator();
        BoxService service = new BoxService(repository, creator, mtgIOClient, scryfallClient);
        return new BoxFacade(repository, creator, service);
    }
}

