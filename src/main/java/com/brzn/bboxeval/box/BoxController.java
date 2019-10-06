package com.brzn.bboxeval.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/box")
public class BoxController {


    private BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping("/evaluation/{setName}")
    public ResponseEntity<BoxEvaluation> getBoxEvaluation(@PathVariable("setName") String setName ){
        return ResponseEntity.ok(boxService.getBoxEvaluation(setName));
    }
}
