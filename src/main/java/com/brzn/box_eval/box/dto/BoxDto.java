package com.brzn.box_eval.box.dto;

import io.vavr.collection.Map;
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
    Map<String, String> boosterStructure;
    LocalDate releaseDate;
}


