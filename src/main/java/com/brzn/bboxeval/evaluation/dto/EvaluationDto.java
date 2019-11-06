package com.brzn.bboxeval.evaluation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class EvaluationDto {
    private final LocalDate date;
    private final String cardSetName;
}

