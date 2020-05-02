package com.brzn.box_eval.box.domain;


import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;

interface BoxRepository extends Repository<Box, Long> {
    Box save(Box box);

    Option<Box> findBySetName(String cardSetName);

    Option<Box> findLast();

    Option<LocalDate> findLastReleaseDate();

    void saveAll(List<Box> boxes);
}
