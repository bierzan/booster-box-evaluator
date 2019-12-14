package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import com.brzn.bboxeval.mtgIOClient.domain.MtgIOClient;
import com.brzn.bboxeval.mtgIOClient.dto.CardSet;
import com.brzn.bboxeval.scryfallClient.domain.ScryfallClient;
import com.brzn.bboxeval.scryfallClient.dto.Card;
import io.vavr.collection.List;

import java.time.LocalDate;

class BoxService {

    private BoxRepository repository;
    private MtgIOClient mtgIOClient;
    private ScryfallClient scryfallClient;

    BoxService(BoxRepository repository,
               MtgIOClient mtgIOClient,
               ScryfallClient scryfallClient) {
        this.mtgIOClient = mtgIOClient;
        this.repository = repository;
        this.scryfallClient = scryfallClient;
    }

    List<BoxDto> searchNew() {
        LocalDate lastReleaseDate = repository.findLast().getReleaseDate();
        return searchBoxesReleasedAfter(lastReleaseDate);
    }

    private List<BoxDto> searchBoxesReleasedAfter(LocalDate lastReleaseDate) {
        List<String> setCodes = scryfallClient.findCardsReleasedAfter(lastReleaseDate)
                .map(Card::getSetCode)
                .distinct()
                .orElse(List.empty());

        return mtgIOClient.findSetsByCodes(setCodes)
                .map(this::prepareBoxDto)
                .orElse(List.empty());

    }

    private BoxDto prepareBoxDto(CardSet cardSet) {
        return Box.builder().releaseDate(LocalDate.now()).build().dto();
    }
}

/*todo
update:
-sprawdz ostatnia box wg ostatniej daty <<box getLast
-pobierz karty z data nowsza <<RestClient
-pobierz dane o boxach w ktorych te karty wystapily <<restClient getBoxesReleasedAfter(date)
-pobierz te boxy <<restClitent getBoxData
-zmapuj na Box i zapisz do bazy*/

