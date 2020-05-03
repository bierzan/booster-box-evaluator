package com.brzn.box_eval.box.domain;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
class BoxCommand {
    private final BoxFinder finder;
    private final BoxRepository repository;

    public void findNew(){
        repository.saveAll(findLastReleasedBoxes());
    }

    private List<Box> findLastReleasedBoxes() {
        return repository.findLastReleaseDate()
                .map(finder::findBoxesReleasedAfter)
                .getOrElse(finder::findAllBoxes);
    }
}
