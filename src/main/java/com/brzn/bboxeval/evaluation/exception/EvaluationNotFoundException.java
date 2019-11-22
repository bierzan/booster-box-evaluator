package com.brzn.bboxeval.evaluation.exception;

public class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException(long setId) {
        super(String.format("Evaluation for set with Id: %d not found", setId));
    }
}
