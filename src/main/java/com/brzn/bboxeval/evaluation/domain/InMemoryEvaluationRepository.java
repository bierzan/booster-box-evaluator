package com.brzn.bboxeval.evaluation.domain;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryEvaluationRepository implements EvaluationRepository {
    private ConcurrentHashMap<String, Evaluation> map = new ConcurrentHashMap<>();

    @Override
    public Evaluation save(Evaluation evaluation) {
        map.put(evaluation.getCardSetName(), evaluation);
        return evaluation;
    }
}
