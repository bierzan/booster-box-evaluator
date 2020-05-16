package com.brzn.box_eval.mtg_io_client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CardSet {
    private String code;
    private String name;
    private JsonNode booster; //todo okreslic docelowy obiekt
    private LocalDate releaseDate;
    private CardSetType type;
    private String block;
}
//todo deserializer + sprawdzenie czy mapuje arraye