package com.brzn.box_eval.cache.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class Card {
    String name;
    LocalDate releasedAt;
    String setName;
    String setCode;
}
