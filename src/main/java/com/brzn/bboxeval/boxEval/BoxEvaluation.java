package com.brzn.bboxeval.boxEval;

import lombok.Getter;

@Getter
public class BoxEvaluation {
    private final String cardSetCode;

    BoxEvaluation(String cardSetCode) {
        this.cardSetCode = cardSetCode;
    }
}

