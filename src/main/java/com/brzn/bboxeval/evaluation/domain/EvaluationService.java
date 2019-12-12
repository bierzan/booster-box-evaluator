package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import com.brzn.bboxeval.box.dto.BoxDto;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

class EvaluationService {
    private EvaluationRepository repository;
    private BoxFacade boxFacade;
    private EvaluationCalculator calculator;

    EvaluationService(EvaluationRepository repository,
                      BoxFacade boxFacade,
                      EvaluationCalculator calculator) {
        this.repository = repository;
        this.boxFacade = boxFacade;
        this.calculator = calculator;
    }

    EvaluationDto getLast(String cardSetName) {
        BoxDto boxDto = boxFacade.get(cardSetName);
        Optional<Evaluation> evaluationOpt = repository.findLastBySetId(boxDto.getId());
        return evaluationOpt.orElse(Evaluation.builder().build()).dto();
    }

    EvaluationDto calculate(String cardSetName) {
        BoxDto boxDto = boxFacade.get(cardSetName);
        BigDecimal avgValue = calculator.getAvgValue(boxDto);
        return EvaluationDto.builder()
                .avgValue(avgValue)
                .date(LocalDate.now())
                .build();
    }
}
