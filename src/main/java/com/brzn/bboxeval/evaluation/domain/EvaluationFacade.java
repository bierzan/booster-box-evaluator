package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Transactional
@Slf4j
public class EvaluationFacade {

    private EvaluationService service;

    EvaluationFacade(EvaluationService service) {
        this.service = service;
    }

    public EvaluationDto evaluateBox(String cardSetName) {
        return service.getBoxEvaluation(cardSetName);
    }
}
