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
    public Option<Box> findBySetName(String cardSetName) { //todo zmienic na id
        Option<Box> box = map.get(cardSetName);
        return box;
    }

    @Override
    public Option<Box> findLast() {
        return map
                .values()
                .maxBy(Box::getReleaseDate);
    }
}
