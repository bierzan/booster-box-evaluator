package com.brzn.box_eval.box.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.HashMap;

@Builder
@Value
public class BoxDto {
    private final long id;
    private final String cardSetName;
    private final HashMap boosterStructure;
    private LocalDate releaseDate;


}
