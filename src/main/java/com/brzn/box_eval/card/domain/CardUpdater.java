package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.dto.CardDto;
import com.brzn.box_eval.card.port.CardJsonFileProvider;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@AllArgsConstructor
class CardUpdater {

    private final CardRepository repo;
    private final CardMapper cardMapper;
    private final CardJsonFileProvider cardJsonFileProvider;

    public void update() {
        getRecentCardJsonFile()
                .peek(this::updateCardRepoFromJsonFile);
    }

    private Option<File> getRecentCardJsonFile() {
        return Option.of(repo.findLastCardUpdateDate())
                .flatMap(lastCardUpdateDate -> Option.of(
                        cardJsonFileProvider.getCardsJsonFileReleasedAfter(lastCardUpdateDate)
                ));
    }

    private void updateCardRepoFromJsonFile(File file) {
        log.info("Updating Card Inventory with card data from cards.json file");
        List<CardDto> cards = cardMapper.fromJsonListFile(file);
        repo.updateAll(cards);
    }
}
