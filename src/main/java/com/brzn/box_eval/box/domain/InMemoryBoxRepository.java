package com.brzn.box_eval.box.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

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
    public Option<Box> findLast() {
        return map
                .values()
                .maxBy(Box::getReleaseDate);
    }
}
