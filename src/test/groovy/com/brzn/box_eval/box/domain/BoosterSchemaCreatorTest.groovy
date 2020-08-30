package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.CardRarity
import com.brzn.box_eval.box.vo.BoosterSchema
import com.brzn.box_eval.box.vo.CardSlot
import com.google.common.collect.Lists
import io.vavr.collection.HashSet
import io.vavr.collection.List
import io.vavr.collection.Set
import spock.lang.Specification

import static com.brzn.box_eval.box.dto.CardRarity.*

class BoosterSchemaCreatorTest extends Specification {

    public static final String COMMON_STR = "common"
    public static final String RARE_STR = "rare"
    public static final String MYTHIC_RARE_STR = "mythic rare"
    public static final String UNCOMMON_STR = "uncommon"

    private BoosterSchemaCreator creator = new BoosterSchemaCreator();

    def "should create booster schema from schema array"(){
        given:
        String[][] boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                Lists.newArrayList(UNCOMMON_STR).toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
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

    def "should create empty booster schema from schema array as null"(){
        given:
        String[][] boosterSchemaArray = null;
        when:
        BoosterSchema schema = creator.from(boosterSchemaArray)
        then:
        schema.isEmpty()
    }

    def "should create empty booster schema from empty schema array"(){
        given:
        String[][] boosterSchemaArray = new String[0][0]
        when:
        BoosterSchema schema = creator.from(boosterSchemaArray)
        then:
        schema.isEmpty()
    }

    def "should create booster schema from schema array excluding empty slot"(){
        given:
        String[][] boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                Lists.newArrayList().toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        BoosterSchema expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity>of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        BoosterSchema schema = creator.from(boosterSchemaArray)
        then:
        schema == expectedSchema
    }

    def "should create booster schema from schema array excluding null slot"(){
        given:
        String[][] boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                null,
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        BoosterSchema expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity>of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        BoosterSchema schema = creator.from(boosterSchemaArray)
        then:
        schema == expectedSchema
    }

    def "should create booster schema from schema array excluding null rarities"(){
        given:
        String[][] boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, null).toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        BoosterSchema expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity>of(RARE) as Set<CardRarity>,
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
