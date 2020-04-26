package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.mtg_io_client.domain.MtgIOClient;
import com.brzn.box_eval.scryfall_client.domain.ScryfallClient;
import com.brzn.box_eval.scryfall_client.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

class BoxService {

    private BoxRepository repository;
    private BoxCreator creator;
    private MtgIOClient mtgIOClient;
    private ScryfallClient scryfallClient;

    BoxService(BoxRepository repository,
               BoxCreator creator,
               MtgIOClient mtgIOClient,
               ScryfallClient scryfallClient) {
        this.repository = repository;
        this.creator = creator;
        this.mtgIOClient = mtgIOClient;
        this.scryfallClient = scryfallClient;
    }

    List<BoxDto> searchForNew() { //todo test czy bedzie pusta lista jesli repo nie odpowie lub nie znajdzie
        return repository.findLast()
                .map(Box::getReleaseDate)
                .map(this::searchBoxesReleasedAfter)
                .getOrElse(() -> searchBoxesReleasedAfter(LocalDate.MIN));
    }

    private List<BoxDto> searchBoxesReleasedAfter(LocalDate lastReleaseDate) { //todo test na to jak scryfall lub mtgIO nie odpowiadaja
        return scryfallClient.findCardsReleasedAfter(lastReleaseDate)
                .map(Card::getSetCode)
                .distinct()
                .transform(codes -> mtgIOClient.findSetsByCodes(codes))
                .map(cardSet -> creator.from(cardSet).dto());

    }

}

