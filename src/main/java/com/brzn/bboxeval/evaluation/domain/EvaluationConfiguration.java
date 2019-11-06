package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EvaluationConfiguration {

    private BoxFacade boxFacade;

    @Autowired
    EvaluationConfiguration(BoxFacade boxFacade) {
        this.boxFacade = boxFacade;
    }

    EvaluationFacade evaluationFacade() {
        return evaluationFacade(new InMemoryEvaluationRepository(),boxFacade);
    }

    @Bean
    EvaluationFacade evaluationFacade(EvaluationRepository evaluationRepository,
                                      BoxFacade boxFacade) {
        EvaluationService evaluationService = new EvaluationService(evaluationRepository, boxFacade);
        return new EvaluationFacade(evaluationService);
    }
}
