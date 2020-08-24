package com.brzn.box_eval.box.vo;

import com.brzn.box_eval.box.dto.CardRarity;
import io.vavr.collection.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
@Getter(AccessLevel.NONE)
public class CardSlot {
    Set<CardRarity> allowedRarities;
}
