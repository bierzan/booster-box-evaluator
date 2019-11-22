package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
@Slf4j
public class BoxFacade {
    private BoxRepository boxRepository;
    private BoxCreator boxCreator;

    public BoxFacade(BoxRepository boxRepository, BoxCreator boxCreator) {
        this.boxRepository = boxRepository;
        this.boxCreator = boxCreator;
    }

    public BoxDto add(BoxDto boxDto) {
        Objects.requireNonNull(boxDto);
        Box box = boxCreator.from(boxDto);
        boxRepository.save(box);
        return box.dto();
    }

    public BoxDto get(String cardSetName){
        return boxRepository.findBySetName(cardSetName).dto();
    }

}
