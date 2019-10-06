package com.brzn.bboxeval.box;

import lombok.Getter;

@Getter
public class BoxEvaluation {
    private final String setName;

    BoxEvaluation(String setName) {
        this.setName = setName;
    }
}
