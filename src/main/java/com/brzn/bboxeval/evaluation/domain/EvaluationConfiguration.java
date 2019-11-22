package com.brzn.bboxeval.evaluation.domain;

import com.brzn.bboxeval.box.domain.BoxFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EvaluationConfiguration {

    private BoxFacade boxFacade;

    EvaluationConfiguration(BoxFacade boxFacade) {
        this.boxFacade = boxFacade;
    }

    EvaluationFacade evaluationFacade() {
        return evaluationFacade(new InMemoryEvaluationRepository(), boxFacade);
    }

    @Bean
    EvaluationFacade evaluationFacade(EvaluationRepository evaluationRepository, BoxFacade boxFacade) {
        EvaluationService evaluationService = new EvaluationService(evaluationRepository, boxFacade);
        EvaluationCreator evaluationCreator = new EvaluationCreator();
        return new EvaluationFacade(evaluationService, evaluationCreator, evaluationRepository);
    }
}
