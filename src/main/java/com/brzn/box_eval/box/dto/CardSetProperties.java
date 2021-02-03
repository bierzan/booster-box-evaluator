package com.brzn.box_eval.box.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CardSetProperties {
    private String code;
    private String name;
    private String[][] booster;
    private LocalDate releaseDate;
    private CardSetType type;
    private String block;

    //todo dtozjsona uproszczone - logika w mapperze
}