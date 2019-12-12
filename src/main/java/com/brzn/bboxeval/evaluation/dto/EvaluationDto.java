package com.brzn.bboxeval.evaluation.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    private long id;
    private LocalDate date;
    private long cardSetId;
    private BigDecimal avgValue;
}

