package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.box.exception.BoxNotFoundException;
import io.vavr.collection.List;
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

    public BoxDto add(BoxDto boxDto) { //todo unit test
        return command.add(boxDto);
    }

    public BoxDto findLast() {
        return repository.findLast()
                .map(Box::dto)
                .getOrElseThrow(() -> new BoxNotFoundException("Box with releaseDate not found"));
    }

    public BoxDto get(String cardSetName) {
        return repository.findBySetName(cardSetName)
                .getOrElseThrow(() -> new BoxNotFoundException(cardSetName))
                .dto();
    }

    public List<BoxDto> addMany(List<BoxDto> boxes) {
        return command.addMany(boxes);
    }
}

