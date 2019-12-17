package com.brzn.box_eval.evaluation.domain;

import com.brzn.box_eval.evaluation.dto.EvaluationDto;

class EvaluationCreator {

    Evaluation from(EvaluationDto evaluationDto) {
        return Evaluation.builder()
                .id(evaluationDto.getId())
                .cardSetId(evaluationDto.getCardSetId())
                .date(evaluationDto.getDate())
                .avgValue(evaluationDto.getAvgValue())
                .build();
    }

}
