package com.brzn.box_eval.cache;

import com.brzn.box_eval.infrastructure.client.Client;
import com.brzn.box_eval.scryfall_client.dto.Card;
import com.brzn.box_eval.scryfall_client.dto.CardBulkDataInfo;
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
class CardCacheUpdater {

    private final Client client;
    private final CardCache cache;
    private final ObjectMapper mapper;

    public void update() {
        CardBulkDataInfo bulkData = client.getCardBulkDataInfo();
        if (cache.isOlderThan(bulkData.getUpdatedAt())) {
            downloadRecentCardsData(bulkData.getDownloadUrl());
            updateCardsCacheFromFile();
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

    private void updateCardsCacheFromFile() {
        try {
            log.info("Updating CardCache with card data from cards.json file");
            List<Card> cards = mapper.readValue("cards.json", new TypeReference<List<Card>>() {});
            cache.replaceContent(cards);
        } catch (IOException e) {
            log.info("Can't parse json file to <List<Card>>. CardCache update failed");
            e.printStackTrace();
        }
    }
}
