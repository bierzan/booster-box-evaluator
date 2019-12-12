package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate date;
    private long cardSetId;
    private BigDecimal avgValue;

    EvaluationDto dto(){
        return EvaluationDto.builder()
                .id(id)
                .date(date)
                .avgValue(avgValue)
                .cardSetId(cardSetId)
                .build();
    }
}

