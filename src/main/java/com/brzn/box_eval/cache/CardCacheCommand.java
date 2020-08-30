package com.brzn.box_eval.cache;

import com.brzn.box_eval.infrastructure.client.Client;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class CardCacheCommand {

    private final Client client;
    private final CardCache cache;

    public void update() {
        cache.replace(client.getCards());
    }
}
