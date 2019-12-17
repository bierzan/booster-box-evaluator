package com.brzn.box_eval.mtgIOclient.domain

import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.dto.Card
import groovy.transform.CompileStatic

@CompileStatic
trait SampleSets implements SampleCards {
    CardSet todaySet = from(todayCard)


    CardSet lastWeekSet = from(lastWeekCard)

    static private from(Card card) {
        return CardSet.builder()
                .releaseDate(card.releasedAt)
                .name(card.setName)
                .code(card.setCode)
                .build()
    }
}


