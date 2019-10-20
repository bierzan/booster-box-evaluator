package com.brzn.bboxeval.evaluation.dto;

import lombok.Getter;

@Getter
public class EvaluationDto {
    private final String cardSetName;

    EvaluationDto(String cardSetName) {
        this.cardSetName = cardSetName;
    }
}

