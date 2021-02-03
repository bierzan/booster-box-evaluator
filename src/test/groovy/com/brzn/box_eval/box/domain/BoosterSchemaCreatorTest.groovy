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

    def creator = new BoosterSchemaCreator();
    def boosterSchema

    def "should create booster schema from schema array"() {
        given:
        def boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                Lists.newArrayList(UNCOMMON_STR).toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        def expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity> of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(UNCOMMON) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema == expectedSchema
    }

    def "should create empty booster schema from schema array as null"() {
        given:
        def boosterSchemaArray = null;
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema.isEmpty()
    }

    def "should create empty booster schema from empty schema array"() {
        given:
        def boosterSchemaArray = new String[0][0]
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema.isEmpty()
    }

    def "should create booster schema from schema array excluding empty slot"() {
        given:
        def boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                Lists.newArrayList().toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        def expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity> of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema == expectedSchema
    }

    def "should create booster schema from schema array excluding null slot"() {
        given:
        def boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, MYTHIC_RARE_STR).toArray(),
                null,
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        BoosterSchema expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity> of(RARE, MYTHIC_RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema == expectedSchema
    }

    def "should create booster schema from schema array excluding null rarities"() {
        given:
        def boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, null).toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        def expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity> of(RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema == expectedSchema
    }

    def "should create booster schema from schema array excluding unknown rarities"() {
        def unknownRarityName = "xxx"
        def boosterSchemaArray = Lists.newArrayList(
                Lists.newArrayList(RARE_STR, unknownRarityName).toArray(),
                Lists.newArrayList(COMMON_STR).toArray()
        ).toArray()
        def expectedSchema = prepareBoosterSchema(
                HashSet.<CardRarity> of(RARE) as Set<CardRarity>,
                HashSet.of(COMMON) as Set<CardRarity>)
        when:
        createBoosterSchemaFromArray(boosterSchemaArray)
        then:
        boosterSchema == expectedSchema
    }

    private BoosterSchema createBoosterSchemaFromArray(Object[] boosterSchemaArray) {
        boosterSchema = creator.from(boosterSchemaArray as String[][])
    }

    def prepareBoosterSchema(Set<CardRarity>... sets) {
        List<CardSlot> cardSlots = List.empty();
        sets.each { set -> cardSlots = cardSlots.append(CardSlot.of(set)) }
        return BoosterSchema.of(cardSlots)

    }
}
