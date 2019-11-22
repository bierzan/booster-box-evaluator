package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.evaluation.dto.EvaluationDto;

class EvaluationCreator {

    Evaluation from(EvaluationDto evaluationDto) {
        return Evaluation.builder()
                .id(evaluationDto.getId())
                .cardSetId(evaluationDto.getCardSetId())
                .date(evaluationDto.getDate())
                .build();
    }

}
