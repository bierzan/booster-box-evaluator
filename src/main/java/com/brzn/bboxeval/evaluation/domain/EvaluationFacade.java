package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Objects;

@AllArgsConstructor
@Transactional
@Slf4j
public class EvaluationFacade {

    private EvaluationService service;
    private EvaluationCreator creator;
    private EvaluationRepository repository;

    public EvaluationDto add(EvaluationDto evaluationDto){
        Objects.requireNonNull(evaluationDto);
        Evaluation evaluation = creator.from(evaluationDto);
        repository.save(evaluation);
        return evaluation.dto();
    }

    public EvaluationDto getLast(String cardSetName) {
        return service.getLast(cardSetName);
    }
}
