package com.brzn.box_eval.scryfall_client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@JsonIgnoreProperties
public class Card {
    private final String name;
    private final LocalDate releasedAt;
    private final String setName;
    private final String setCode;
}
