package com.brzn.box_eval.card.domain

import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleCards {
    Card todayCard = Card.builder()
            .uuid("todayCardUUID")
            .name("todayCard")
            .releasedAt(LocalDate.now())
            .setName("todaySet")
            .setCode("ts")
            .build();

    Card lastWeekCard = Card.builder()
            .uuid("lastWeekCardUUID")
            .name("lastWeekCard")
            .releasedAt(LocalDate.now().minusWeeks(1))
            .lastUpdate(LocalDate.now().minusWeeks(1))
            .setName("lastWeekSet")
            .setCode("lws")
            .build();
}
