package com.brzn.bboxeval.box.domain;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
class InMemoryBoxRepository implements BoxRepository {
    private ConcurrentHashMap<String, Box> map = new ConcurrentHashMap<>();

    @Override
    public Box save(Box box) {
        map.put(box.getCardSetName(), box);
        return box;
    }
}
