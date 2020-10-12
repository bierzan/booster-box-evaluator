package com.brzn.box_eval.evaluation.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@AllArgsConstructor
@Transactional
@Slf4j
public class EvaluationFacade {

    private EvaluationService service;
    private EvaluationCreator creator;
    private EvaluationRepository repository;
}
