package com.brzn.box_eval.scryfall_client;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
class ScryfallRestTemplate {

    public static final String URL_DEFAULT_BULK_DATA_INFO = "https://api.scryfall.com/bulk-data/e2ef41e3-5778-4bc2-af3f-78eca4dd9c23";
    private final RestTemplate restTemplate;

    public ResponseEntity<String> getBulkCardUrl() {
        return restTemplate.getForEntity(URL_DEFAULT_BULK_DATA_INFO, String.class);
    };
}
