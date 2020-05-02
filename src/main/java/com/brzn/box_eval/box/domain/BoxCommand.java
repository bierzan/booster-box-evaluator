package com.brzn.box_eval.box.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
class BoxCommand {
    private final BoxCreator creator;
    private final BoxFinder finder;
    private final BoxRepository repository;

    public void findNew(){
        repository.findLastReleaseDate()
                .map(finder::findBoxesReleasedAfter)
                .peek(repository::saveAll);
    }
}
