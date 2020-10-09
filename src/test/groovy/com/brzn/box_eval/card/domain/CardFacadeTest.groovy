package com.brzn.box_eval.card.domain

import com.brzn.box_eval.infrastructure.client.RestClient
import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

class CardFacadeTest extends Specification implements SampleCards{
    def repo = new InMemoryCardRepository()
    def client = Mock(RestClient)
    def provider = new CardProvider(repo)
    def updater = new CardUpdater(client, repo, new ObjectMapper())
    def facade = new CardFacade(updater, provider)

    def "update acceptance test"() {
        given: "Repo updated with some cards"
        repo.updateAll()

        when: "I invoke update"
        facade.updateCardRepository()
        then: "I see repo with new cards and recent update date"
        then: "I invoke update once more"
        then: "I see that cards and update date haven't changed"
    }
}
