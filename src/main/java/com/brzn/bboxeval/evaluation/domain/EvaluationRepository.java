package com.brzn.bboxeval.evaluation.domain;


import org.springframework.data.repository.Repository;

interface EvaluationRepository extends Repository<Evaluation, Long> {
    Evaluation save(Evaluation evaluation);
}
