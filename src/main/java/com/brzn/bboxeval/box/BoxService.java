package com.brzn.bboxeval.box;

import org.springframework.stereotype.Service;

@Service
public class BoxService {

    public BoxEvaluation getBoxEvaluation(String setName){
        return new BoxEvaluation(setName);
    }
}
