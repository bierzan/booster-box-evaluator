package com.brzn.box_eval.cache;

import com.brzn.box_eval.infrastructure.client.Client;
import com.brzn.box_eval.scryfall_client.dto.CardBulkDataInfo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CardCacheCommand {

    private final Client client;
    private final CardCache cache;

    public void update() {

        CardBulkDataInfo bulkData = client.getCardBulkDataInfo();
        if(cache.isOlderThan(bulkData.getUpdatedAt()))


        cache.replace(client.getAllCards());
/*
        todo
         - odpytaj o bulkData
         - sprawdz z ostatnim czasem aktualizowania cache
         - jesli data z bulka jest nowsza sciagnij plik
         - parsuj na Card
         -aktualizuj Cache
*/

    }
}
