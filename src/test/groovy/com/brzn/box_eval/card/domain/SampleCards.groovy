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
            .price(new BigDecimal("0.1"))
            .build();

    Card lastWeekCard = Card.builder()
            .uuid("lastWeekCardUUID")
            .name("lastWeekCard")
            .releasedAt(LocalDate.now().minusWeeks(1))
            .lastUpdate(LocalDate.now().minusWeeks(1))
            .setName("lastWeekSet")
            .setCode("lws")
            .price(new BigDecimal("6.01"))
            .build();

    Card veryOldCard = Card.builder()
            .uuid("veryOldUUID")
            .name("veryOldCard")
            .releasedAt(LocalDate.MIN)
            .lastUpdate(LocalDate.MIN)
            .setName("veryOldSet")
            .setCode("vos")
            .price(new BigDecimal("122.21"))
            .build();
}
