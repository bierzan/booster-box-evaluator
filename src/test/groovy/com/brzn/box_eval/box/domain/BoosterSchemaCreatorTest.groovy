package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.CardRarity
import com.brzn.box_eval.box.vo.BoosterSchema
import com.brzn.box_eval.box.vo.CardSlot
import com.google.common.collect.Lists
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import static com.brzn.box_eval.box.dto.CardRarity.*

class BoosterSchemaCreatorTest extends Specification {

    private BoosterSchemaCreator creator = new BoosterSchemaCreator();

    def "should create booster schema from schema array"(){
        given:
        String[][] boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList("rare", "mythic rare").toArray(),
                Lists.newArrayList("uncommon").toArray(),
                Lists.newArrayList("common").toArray()
        ).toArray()
        BoosterSchema expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity>of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(UNCOMMON) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        BoosterSchema schema = creator.from(boosterSchemaArray)
        then:
        schema == expectedSchema
    }

    private BoosterSchema prepareBoosterSchema(Set<CardRarity>...sets) {
        List<CardSlot> cardSlots = List.empty();
        sets.each { set -> cardSlots = cardSlots.append(CardSlot.of(set))}
        return BoosterSchema.of(cardSlots)

    }
}
