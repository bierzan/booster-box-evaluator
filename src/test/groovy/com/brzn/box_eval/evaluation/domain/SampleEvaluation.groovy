package com.brzn.box_eval.evaluation.domain

import com.brzn.box_eval.evaluation.dto.EvaluationDto
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleEvaluation {
    EvaluationDto sampleEvaluation = createEvaluationDto(LocalDate.MIN, BigDecimal.ONE);

    static private EvaluationDto createEvaluationDto(LocalDate date, BigDecimal avgValue) {
        return EvaluationDto.builder()
                .avgValue(avgValue)
                .date(date)
                .build();
    }

}


