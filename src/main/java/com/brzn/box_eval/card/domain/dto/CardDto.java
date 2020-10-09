package com.brzn.box_eval.card.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CardDto {
    private long id;
    private long uuid;
    private String name;
    private LocalDate releasedAt;
    private String setName;
    private String setCode;
    private BigDecimal price;
}
