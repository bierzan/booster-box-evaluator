package com.brzn.box_eval.box.dto;

import com.brzn.box_eval.box.vo.BoosterSchema;
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
    String cardSetCode;
    BoxCardSetType boxCardSetType;
    LocalDate releaseDate;
    BoosterSchema boosterSchema;
    int boosterQuantity;
}


