package com.brzn.box_eval.box.domain;


import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

interface BoxRepository extends Repository<Box, Long> {
    Box save(Box box);

    Option<Box> findBySetName(String cardSetName);

    Option<Box> findLast();
}
