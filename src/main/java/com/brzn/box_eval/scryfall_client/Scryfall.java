package com.brzn.box_eval.scryfall_client;

import com.brzn.box_eval.scryfall_client.dto.CardBulkDataInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Scryfall {
    private final ScryfallRestTemplate restTemplate;

    public CardBulkDataInfo getCardBulkDataInfo() {
        return restTemplate.getCardBulkDataInfo();
    }
}
