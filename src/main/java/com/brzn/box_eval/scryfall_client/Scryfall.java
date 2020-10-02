package com.brzn.box_eval.scryfall_client;

import com.brzn.box_eval.scryfall_client.dto.Card;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

@AllArgsConstructor
public class Scryfall {
    private final ScryfallRestTemplate restTemplate;
    private final ObjectMapper mapper;

    public List<Card> getAllCards() throws IOException { //todo testy i implementacja
        ResponseEntity<String> response = restTemplate.getBulkCardUrl();
        JsonNode json = mapper.readTree(response.getBody());
        String url = json.path("download_uri").textValue();
        return Arrays.stream(mapper.readValue(new URL(url), Card[].class))
                .collect(List.collector());
    }
}
