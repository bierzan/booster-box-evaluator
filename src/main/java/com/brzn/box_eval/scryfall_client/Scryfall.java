package com.brzn.box_eval.scryfall_client;

import com.brzn.box_eval.scryfall_client.dto.CardBulkDataInfo;
import lombok.AllArgsConstructor;

import java.net.URL;
import java.time.LocalDate;

@AllArgsConstructor
public class Scryfall {
    private final ScryfallRestTemplate restTemplate;

    public URL getUrlForCardDateUpdatedAfter(LocalDate lastUpdate) {
        CardBulkDataInfo bulkData = restTemplate.getCardBulkDataInfo();
        if(bulkData.wasUpDatedAfter(lastUpdate)){
            return bulkData.getDownloadUrl();
        }
        return null;
    }
}
