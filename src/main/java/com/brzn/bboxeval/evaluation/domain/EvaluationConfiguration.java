package com.brzn.bboxeval.evaluation.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvaluationConfiguration {

    EvaluationFacade evaluationFacade(){
        EvaluationRepository repo = new InMemoryEvaluationRepository();
        return evaluationFacade(repo);
    }

    @Bean
    EvaluationFacade evaluationFacade(EvaluationRepository evaluationRepository){
        return  new EvaluationFacade();
    }
}
