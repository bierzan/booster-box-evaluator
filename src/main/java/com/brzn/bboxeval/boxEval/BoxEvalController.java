package com.brzn.bboxeval.boxEval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/box")
public class BoxEvalController {

    private BoxEvalService boxEvalService;

    @Autowired
    public BoxEvalController(BoxEvalService boxEvalService) {
        this.boxEvalService = boxEvalService;
    }

    @GetMapping("/evaluation/{cardSetCode}")
    public ResponseEntity<BoxEvaluation> getBoxEvaluation(@PathVariable("cardSetCode") String cardSetCode) {
        Optional<BoxEvaluation> optionalBoxEvaluation = boxEvalService.getBoxEvaluation(cardSetCode);
        if(optionalBoxEvaluation.isPresent()) {
            return  ResponseEntity.ok(optionalBoxEvaluation.get());
        }
        return ResponseEntity.notFound().build();
    }
}

