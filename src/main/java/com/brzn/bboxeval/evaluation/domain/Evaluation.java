package com.brzn.bboxeval.evaluation.domain;

import lombok.Getter;

@Getter
class Evaluation {
    private final String cardSetName;

    Evaluation(String cardSetName) {
        this.cardSetName = cardSetName;
    }
}

