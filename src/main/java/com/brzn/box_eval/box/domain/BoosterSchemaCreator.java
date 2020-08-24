package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.vo.BoosterSchema;
import com.brzn.box_eval.box.dto.CardRarity;
import com.brzn.box_eval.box.vo.CardSlot;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
class BoosterSchemaCreator {

    public BoosterSchema from(String[][] schemaAsArray) {
        if (schemaAsArray == null) { //todo test
            log.info("boosterSchema is null");
            return null;
        }

        List<CardSlot> slots = List.empty();

        for (String[] slot : schemaAsArray) {

            Set<CardRarity> rarities = Arrays.stream(slot)
                    .map(this::findMatchingRarity)
                    .collect(HashSet.collector());

            slots = slots.append(CardSlot.of(rarities));
        }
        return BoosterSchema.of(slots);
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
