package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.CardRarity;
import com.brzn.box_eval.box.vo.BoosterSchema;
import com.brzn.box_eval.box.vo.CardSlot;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
class BoosterSchemaCreator {

    public BoosterSchema from(String[][] schemaAsArray) {

        if (isNull(schemaAsArray) || schemaAsArray.length == 0) {
            log.info("created empty BoosterSchema");
            return BoosterSchema.of(null);
        }

        List<CardSlot> slots = Arrays.stream(schemaAsArray)
               .filter(slot -> nonNull(slot) && slot.length!=0)
               .map(this::getRaritiesForSlot)
               .map(CardSlot::of)
               .collect(List.collector());

        return BoosterSchema.of(slots);
    }

    private HashSet<CardRarity> getRaritiesForSlot(String[] slot) {
        return Arrays.stream(slot)
                .filter(Objects::nonNull)
                .map(this::findMatchingRarity)
                .collect(HashSet.collector());
    }

    private CardRarity findMatchingRarity(String name) {
        for (CardRarity rarity : CardRarity.values()) {
            if (rarity.getRarityName().equals(name)) {
                return rarity;
            }
        }
        return null;
    }
}
