package com.brzn.box_eval.box.domain;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@AllArgsConstructor
public class BoxFacade {
    private final BoxCommand command;

    public List<Long> findNew(){
        return command.findNew();
    }
}

