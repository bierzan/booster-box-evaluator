package com.brzn.bboxeval.evaluation.domain


import com.brzn.bboxeval.evaluation.dto.EvaluationDto
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleEvaluation {
    EvaluationDto sampleEvaluation = createEvaluationDto(LocalDate.now().minusDays(2));

    static private EvaluationDto createEvaluationDto(LocalDate date) {
        return EvaluationDto.builder()
                .date(date)
                .build();
    }

}


