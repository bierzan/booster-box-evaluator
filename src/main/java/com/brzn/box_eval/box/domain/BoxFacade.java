package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.box.exception.BoxNotFoundException;
import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Transactional
@Slf4j
@AllArgsConstructor
public class BoxFacade {
    private final BoxCommand command;
    private final BoxRepository repository;

    public List<Long> findNew(){
        return command.findNew();
    }

    public BoxDto get(String cardSetName) {
        return repository.findBySetName(cardSetName)
                .getOrElseThrow(() -> new BoxNotFoundException(cardSetName))
                .dto();
    }
}

