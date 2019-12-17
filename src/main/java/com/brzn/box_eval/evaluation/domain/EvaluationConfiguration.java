package com.brzn.box_eval.evaluation.domain;

import com.brzn.box_eval.box.domain.BoxFacade;
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
    EvaluationFacade evaluationFacade(EvaluationRepository repository, BoxFacade boxFacade) {
        EvaluationCreator creator = new EvaluationCreator();
        EvaluationService service = new EvaluationService(repository, boxFacade, new EvaluationCalculator());
        return new EvaluationFacade(service, creator, repository);
    }
}
