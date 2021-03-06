package com.brzn.box_eval.evaluation;

import com.brzn.box_eval.evaluation.domain.EvaluationFacade;
import com.brzn.box_eval.evaluation.dto.EvaluationDto;
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
        return null;
    }
}

