package com.brzn.box_eval.box.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.control.Option;
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
    public Option<Box> findBySetName(String cardSetName) {
        return map.get(cardSetName);
    }

    @Override
    public Option<LocalDate> findLastReleaseDate() {
        return map.values()
                .maxBy(Box::getReleaseDate)
                .map(Box::getReleaseDate);
    }

    @Override
    public List<Long> saveAll(List<Box> boxes) {
        return boxes.map(this::save)
                .map(Box::getId);
    }

    public List<Box> findAll() {
        return map.values().toList();
    }
}
