package com.brzn.box_eval.box.vo;

import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
@Getter(AccessLevel.NONE)
public class BoosterSchema {
    List<CardSlot> cardSlots;
}
