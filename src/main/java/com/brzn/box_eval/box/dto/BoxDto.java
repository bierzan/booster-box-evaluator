package com.brzn.box_eval.box.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.HashMap;

@Builder
@Value
@ToString
public class BoxDto {
    @ToString.Exclude
    private long id;
    private String cardSetName;
    @ToString.Exclude
    private HashMap boosterStructure;
    private LocalDate releaseDate;
}


