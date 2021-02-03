package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.CardRarity;
import com.brzn.box_eval.box.vo.BoosterSchema;
import com.brzn.box_eval.box.vo.CardSlot;
import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;
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

    private Set<CardRarity> getRaritiesForSlot(String[] slot) {
        return Arrays.stream(slot)
                .map(this::findMatchingRarity)
                .filter(Objects::nonNull)
                .collect(HashSet.collector());
    }

    private CardRarity findMatchingRarity(String name) {
        for (CardRarity rarity : CardRarity.values()) {
            if (rarity.getRarityName().equals(name)) {
                return rarity;
            }
        }
        log.info(String.format("Couldn't find matching rarity for name [%s]", name));
        return null;
    }
}
