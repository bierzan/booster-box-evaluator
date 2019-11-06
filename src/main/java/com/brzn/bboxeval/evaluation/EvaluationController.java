package com.brzn.bboxeval.evaluation;

import com.brzn.bboxeval.evaluation.domain.EvaluationFacade;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation")
class EvaluationController {

    private EvaluationFacade evaluationFacade;

    public EvaluationController(EvaluationFacade evaluationFacade) {
        this.evaluationFacade = evaluationFacade;
    }

    @GetMapping("/box/{cardSetName}")
    public ResponseEntity<EvaluationDto> getBoxEvaluation(@PathVariable("cardSetCode") String cardSetName) {
        EvaluationDto evaluationDto = evaluationFacade.evaluateBox(cardSetName);
        return ResponseEntity.ok(evaluationDto);
    }
}

