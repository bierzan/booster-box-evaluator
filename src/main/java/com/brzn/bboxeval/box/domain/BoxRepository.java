package com.brzn.bboxeval.box.domain;


import org.springframework.data.repository.Repository;

interface BoxRepository extends Repository<Box, Long> {
    Box save(Box box);
}
