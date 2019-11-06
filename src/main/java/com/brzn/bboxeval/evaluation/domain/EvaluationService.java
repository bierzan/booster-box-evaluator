package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import com.brzn.bboxeval.box.dto.BoxDto;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

class EvaluationService {
    private EvaluationRepository repository;
    private BoxFacade boxFacade;

    @Autowired
    EvaluationService(EvaluationRepository repository, BoxFacade boxFacade) {
        this.repository = repository;
        this.boxFacade = boxFacade;
    }

    EvaluationDto getBoxEvaluation(String cardSetName) {
        EvaluationDto evaluationDto = repository.findLastEvaluation(cardSetName);
        if (checkIfEvaluationIsOlderThanWeek(evaluationDto)) {
            BoxDto boxDto = boxFacade.getBoxBySet(cardSetName);
            List<Card> cards = getRecentCardsData();
            Evaluation evaluation = prepareEvaluation(boxDto, cards);
            //https://scryfall.com/docs/api/cards/search
            return evaluation.toDto();
        }
        return null;
    }

    private boolean checkIfEvaluationIsOlderThanWeek(EvaluationDto evaluationDto){
        return evaluationDto.getDate().plusWeeks(1).isBefore(LocalDate.now());
    }
}
