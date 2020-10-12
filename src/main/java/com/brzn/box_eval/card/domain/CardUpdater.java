package com.brzn.box_eval.card.domain;

import com.brzn.box_eval.card.domain.dto.CardDto;
import com.brzn.box_eval.infrastructure.client.Client;
import com.brzn.box_eval.scryfall_client.dto.CardBulkDataInfo; //todo przeniesc do domeny card
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
@AllArgsConstructor
class CardUpdater {

    private final Client client; //todo provider
    private final CardRepository repo;
    private final ObjectMapper mapper; //todo card mapper

    public void update() {
        CardBulkDataInfo bulkData = client.getCardBulkDataInfo();
        if (repo.isOlderThan(bulkData.getUpdatedAt())) {
            downloadRecentCardsData(bulkData.getDownloadUrl());
            updateCardRepoFromFile();
        }
    }

    private void downloadRecentCardsData(URL url) {
        try {
            log.info(String.format("Downloading file from url %s", url.toString()));
            FileUtils.copyURLToFile(url, new File("cards.json"));
        } catch (IOException e) {
            log.info(String.format("Downloading file from url %s failed. card.json file not saved", url.toString()));
            e.printStackTrace();
        }
    }

    private void updateCardRepoFromFile() {
        try {
            log.info("Updating CardCache with card data from cards.json file");
            List<CardDto> cards = mapper.readValue("cards.json", new TypeReference<List<CardDto>>() {});
            repo.updateAll(cards); //todo czy replace content jest bezpieczny kiedy przyjda bledne lub okrojone dane - update lepszy?
        } catch (IOException e) {
            log.info("Can't parse json file to <List<Card>>. CardCache update failed");
            e.printStackTrace();
        }
    }
}