package com.brzn.box_eval.box.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.HashMap;

@Builder
@Value
public class BoxDto {
    @ToString.Exclude
    long id;
    String cardSetName;
    @ToString.Exclude
    HashMap boosterStructure; //todo zmienic na interfejs
    LocalDate releaseDate;
}


