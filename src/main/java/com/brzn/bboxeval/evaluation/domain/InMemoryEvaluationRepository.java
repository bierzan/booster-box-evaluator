package com.brzn.bboxeval.evaluation.domain;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryEvaluationRepository implements EvaluationRepository {
    private ConcurrentHashMap<Long, Evaluation> map = new ConcurrentHashMap<>();

    @Override
    public Evaluation save(Evaluation evaluation) {
        map.put(evaluation.getId(), evaluation);
        return evaluation;
    }

    @Override
    public Optional<Evaluation> findLastBySetId(long setId) {
        return map.values().stream()
                .filter(evaluation -> setId == evaluation.getCardSetId())
                .max(Comparator.comparing(Evaluation::getDate));
    }
}
