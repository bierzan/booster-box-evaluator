package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.box.exception.BoxSaveInterruptionException;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
class BoxCommand {
    private BoxCreator creator;
    private BoxRepository repository;

    public BoxDto add(BoxDto boxDto) { //todo unit test czy dostalo Id
        return Option.of(boxDto)
                .map(creator::from)
                .map(repository::save)
                .map(Box::dto)
                .getOrElseThrow(() -> new BoxSaveInterruptionException(boxDto));
    }

    public List<BoxDto> addMany(List<BoxDto> boxes) { //todo test na liste z paroma nullami
        return boxes
                .map(boxDto -> add(boxDto))
                .toList();
    }
}
