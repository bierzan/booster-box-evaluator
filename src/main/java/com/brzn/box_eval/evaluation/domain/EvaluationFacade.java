package com.brzn.box_eval.evaluation.domain;

import com.brzn.box_eval.evaluation.dto.EvaluationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
@Slf4j
public class EvaluationFacade {

    private EvaluationService service;
    private EvaluationCreator creator;
    private EvaluationRepository repository;

    public EvaluationDto getLast(String cardSetName) {
        return service.getLast(cardSetName);
    }

    public EvaluationDto calculate(String cardSetName) {
        return service.calculate(cardSetName);
    }
}
