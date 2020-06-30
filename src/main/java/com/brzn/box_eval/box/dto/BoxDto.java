package com.brzn.box_eval.box.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class BoxDto {
    @ToString.Exclude
    long id;
    String cardSetName;
    @ToString.Exclude
    Booster booster;
    LocalDate releaseDate;
}


