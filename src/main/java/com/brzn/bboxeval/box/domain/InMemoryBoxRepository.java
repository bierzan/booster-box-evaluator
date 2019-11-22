package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.exception.BoxNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryBoxRepository implements BoxRepository {
    private ConcurrentHashMap<String, Box> map = new ConcurrentHashMap<>();

    @Override
    public Box save(Box box) {
        map.put(box.getCardSetName(), box);
        return box;
    }

    @Override
    public Box findBySetName(String cardSetName) {
        Box box = map.get(cardSetName);
        if(Objects.isNull(box))
                throw new BoxNotFoundException(cardSetName);
        return box;
    }
}
