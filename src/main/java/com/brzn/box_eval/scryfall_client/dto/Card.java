package com.brzn.box_eval.scryfall_client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@JsonIgnoreProperties
public class Card {
    long id;
    long uuid;
    String name;
    LocalDate releasedAt;
    String setName;
    String setCode;
}
