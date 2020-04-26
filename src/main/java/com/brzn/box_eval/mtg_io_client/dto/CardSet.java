package com.brzn.box_eval.mtg_io_client.dto;

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
    private final CardSetType type;
    private final String block;
}
//todo deserializer + sprawdzenie czy mapuje arraye