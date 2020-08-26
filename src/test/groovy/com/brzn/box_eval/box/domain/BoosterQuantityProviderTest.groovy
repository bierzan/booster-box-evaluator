package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.BoxCardSetType
import spock.lang.Specification

class BoosterQuantityProviderTest extends Specification {
    private BoosterQuantityProvider provider = new BoosterQuantityProvider();

    def "getBoosterQuantity should return 24 for Conspiracy block"(){
        given:
        Box box = Box.builder().cardSetBlock("Conspiracy").build()
        when:
        def quantity = provider.getBoosterQuantity(box)
        then:
        quantity == 24
    }

    def "getBoosterQuantity should return 36 for standardBlock"(){
        given:
        Box box = Box.builder().cardSetBlock("standardBlock").build()
        when:
        def quantity = provider.getBoosterQuantity(box)
        then:
        quantity == 36
    }

    def "getBoosterQuantity should return 24 for standardBlock and Masters set"(){
        given:
        Box box = Box.builder()
                .cardSetBlock("standardBlock")
                .boxCardSetType(BoxCardSetType.MASTERS)
                .build()
        when:
        def quantity = provider.getBoosterQuantity(box)
        then:
        quantity == 24
    }

    def "getBoosterQuantity should return 36 for standardBlock and any other set"(){
        given:
        Box box = Box.builder()
                .cardSetBlock("standardBlock")
                .boxCardSetType(BoxCardSetType.CORE)
                .build()
        when:
        def quantity = provider.getBoosterQuantity(box)
        then:
        quantity == 36
    }
}
