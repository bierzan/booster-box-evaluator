package com.brzn.box_eval.adapter.card.provider

import com.brzn.box_eval.infrastructure.client.Client
import spock.lang.Specification

import java.time.LocalDate

class DefaultCardJsonFileProviderTest extends Specification {
    def client = Mock(Client)
    def fileProvider = new DefaultCardJsonFileProvider(client)

    def "should provide file"(){
        when:
        fileProvider.getCardsJsonFileReleasedAfter(LocalDate.now())
        then:
        1==2
    }
}
