package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxDto;
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
        String setName = box.dto().getCardSetName();
        map = map.put(setName, box);
        return box;
    }

    @Override
    public Option<LocalDate> findLastReleaseDate() {
        return map.values()
                .map(Box::dto)
                .maxBy(BoxDto::getReleaseDate)
                .map(BoxDto::getReleaseDate);
    }

    @Override
    public List<Long> saveAll(List<Box> boxes) {
        return boxes.map(this::save)
                .map(Box::dto)
                .map(BoxDto::getId);
    }

    public List<Box> findAll() {
        return map.values().toList();
    }
}
