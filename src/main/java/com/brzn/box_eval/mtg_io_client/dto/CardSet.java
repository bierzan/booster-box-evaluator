package com.brzn.box_eval.mtg_io_client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
    @JsonDeserialize(using = BoosterDeserializer.class)
    private String[][] booster;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate releaseDate;
    private CardSetType type;
    private String block;
}