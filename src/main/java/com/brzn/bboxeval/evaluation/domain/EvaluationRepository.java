package com.brzn.bboxeval.evaluation.domain;


import org.springframework.data.repository.Repository;

import java.util.Optional;

interface EvaluationRepository extends Repository<Evaluation, Long> {
    Evaluation save(Evaluation evaluation);

    Optional<Evaluation> findLastBySetId(long id);
}
