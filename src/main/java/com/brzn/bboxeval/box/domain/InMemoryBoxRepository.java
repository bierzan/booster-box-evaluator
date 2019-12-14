package com.brzn.bboxeval.box.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
class InMemoryBoxRepository implements BoxRepository {
    private Map<String, Box> map = HashMap.empty();

    @Override
    public Box save(Box box) {
        map = map.put(box.getCardSetName(), box);
        return box;
    }

    @Override
    public Box findBySetName(String cardSetName) {
        return null;
    }

    @Override
    public Box findLast() {
        return map
                .values()
                .maxBy(Box::getReleaseDate)
                .getOrElse(Box.builder()
                        .releaseDate(LocalDate.MIN)
                        .build());
    }
}
