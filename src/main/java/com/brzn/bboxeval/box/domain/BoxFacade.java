package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Transactional
@Slf4j
public class BoxFacade {
    private BoxRepository repository;
    private BoxCreator creator;
    private BoxService service;

    BoxFacade(BoxRepository repository, BoxCreator creator, BoxService service) {
        this.repository = repository;
        this.creator = creator;
        this.service = service;
    }

    public BoxDto add(BoxDto boxDto) {
        Option<Box> boxOpt = Option.of(boxDto)
                .map(dto -> creator.from(boxDto))
                .peek(box-> repository.save(box));
        return boxOpt.getOrElse(Box.blank()).dto();
    }

    public BoxDto findLast() {
        return repository.findLast().dto();
    }

    public BoxDto get(String cardSetName) {
        return repository.findBySetName(cardSetName).dto();
    }

    public List<BoxDto> searchNew() {
        return service.searchNew();
    }
}

