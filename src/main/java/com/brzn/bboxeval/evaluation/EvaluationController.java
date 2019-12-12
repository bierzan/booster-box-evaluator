package com.brzn.bboxeval.evaluation;

import com.brzn.bboxeval.evaluation.domain.EvaluationFacade;
import com.brzn.bboxeval.evaluation.dto.EvaluationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
class EvaluationController {

    private EvaluationFacade evaluationFacade;

    public EvaluationController(EvaluationFacade evaluationFacade) {
        this.evaluationFacade = evaluationFacade;
    }

    @GetMapping("/box")
    public ResponseEntity<EvaluationDto> getLastBoxEvaluation(@RequestParam("setName") String cardSetName) {
        return ResponseEntity.ok().body(evaluationFacade.getLast(cardSetName));
    }

    @PostMapping("/calculate/box")
    public ResponseEntity<EvaluationDto> calculateBoxEvaluation(@RequestParam("setName") String cardSetName) {
        return ResponseEntity.ok().body(evaluationFacade.calculate(cardSetName));
    }
}

