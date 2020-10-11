package com.brzn.box_eval.box.domain


import com.brzn.box_eval.card.domain.dto.CardDto
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleCardDtos {
    CardDto todayCard = CardDto.builder()
            .name("todayCard")
            .releasedAt(LocalDate.now())
            .setName("todaySet")
            .setCode("ts")
            .build();

    CardDto lastWeekCard = CardDto.builder()
            .name("lastWeekCard")
            .releasedAt(LocalDate.now().minusWeeks(1))
            .setName("lastWeekSet")
            .setCode("lws")
            .build();
}
