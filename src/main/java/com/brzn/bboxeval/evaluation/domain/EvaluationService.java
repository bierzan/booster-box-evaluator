package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import com.brzn.bboxeval.box.dto.BoxDto;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import org.springframework.beans.factory.annotation.Autowired;

class EvaluationService {
    private EvaluationRepository repository;
    private BoxFacade boxFacade;

    @Autowired
    EvaluationService(EvaluationRepository repository, BoxFacade boxFacade) {
        this.repository = repository;
        this.boxFacade = boxFacade;
    }

    EvaluationDto getLast(String cardSetName) {
        BoxDto boxDto = boxFacade.get(cardSetName);
        return repository.findLastBySetId(boxDto.getId()).get().dto();

    }
}
