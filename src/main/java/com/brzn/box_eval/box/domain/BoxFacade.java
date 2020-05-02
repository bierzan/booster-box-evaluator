package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.box.exception.BoxNotFoundException;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Transactional
@Slf4j
public class BoxFacade {
    private final BoxCommand command;
    private final BoxRepository repository;

    public BoxFacade(BoxCommand command, BoxRepository repository) {
        this.command = command;
        this.repository = repository;
    }

    public void findNew(){
        command.findNew();
    }

    public BoxDto get(String cardSetName) {
        return repository.findBySetName(cardSetName)
                .getOrElseThrow(() -> new BoxNotFoundException(cardSetName))
                .dto();
    }
}

