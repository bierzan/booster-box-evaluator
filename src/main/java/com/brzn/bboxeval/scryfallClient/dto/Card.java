package com.brzn.bboxeval.scryfallClient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;

import java.time.LocalDate;

@Value
@JsonIgnoreProperties
public class Card {
    private final String name;
    private final LocalDate releasedAt;
    private final String setName;
    private final String setCode;
}
