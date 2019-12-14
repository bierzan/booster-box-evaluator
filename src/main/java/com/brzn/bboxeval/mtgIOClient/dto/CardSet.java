package com.brzn.bboxeval.mtgIOClient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@JsonIgnoreProperties
public class CardSet {
    private final String code;
    private final String name;
    private final String booster;
    private final LocalDate releaseDate;
}
//todo deserializer + sprawdzenie czy mapuje arraye