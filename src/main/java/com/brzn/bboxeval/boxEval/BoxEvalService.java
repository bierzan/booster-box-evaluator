package com.brzn.bboxeval.boxEval;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoxEvalService {

    public Optional<BoxEvaluation> getBoxEvaluation(String cardSetCode) {
        return Optional.of(new BoxEvaluation(cardSetCode));
    }
}
