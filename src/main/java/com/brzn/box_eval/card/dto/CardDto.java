package com.brzn.box_eval.card.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDto {
    @JsonIgnore
    private long id;
    @JsonProperty(value = "id")
    private String uuid;
    private String name;
    private LocalDate releasedAt;
    private String setName;
    private String setCode;
    private BigDecimal price;
}
