package com.brzn.box_eval.mtg_io_client


import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray
import com.google.common.collect.Lists
import io.vavr.collection.HashSet
import nl.altindag.log.LogCaptor
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus

class MtgIORestTemplateTest extends Specification implements SampleCardSets {
    public static final String GET_SETS_BY_NAMES_URL = "https://api.magicthegathering.io/v1/sets?name="
    private static final String GET_ALL_CARDSETS = "https://api.magicthegathering.io/v1/sets";

    def restTemplate = new RestTemplate()
    def mockServer = MockRestServiceServer.createServer(restTemplate);
    def mtgIORestTemplate = new MtgIORestTemplate(restTemplate);
    def objectMapper = new Jackson2ObjectMapperBuilder().simpleDateFormat("yyyy-MM-dd").build()
    def logCaptor = LogCaptor.forClass(MtgIORestTemplate.class);

    def "getCardSetsArrayByCardSetsNames should return and deserialize sampleSet"() {
        given:
        def searchedCardSets = HashSet.of(sampleCommonSet.name)
        def JSONCardSetArray = getJSONCardSetArrayFromCardSets(sampleCommonSet)
        def expectedCardSetsArray = new CardSetsArray(Lists.newArrayList(sampleCommonSet))

        mockServer.expect(requestTo(GET_SETS_BY_NAMES_URL + encodeURLQuery(sampleCommonSet.name)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(JSONCardSetArray));
        when:
        def result = mtgIORestTemplate.getCardSetsArrayByCardSetsNames(searchedCardSets)
        then:
        mockServer.verify()
        result == expectedCardSetsArray
    }

    def "getCardSetsArrrayOfAllCardSets should return and deserialize sampleSet"() {
        given:
        def JSONCardSetArray = getJSONCardSetArrayFromCardSets(sampleCommonSet)
        def expectedCardSetsArray = new CardSetsArray(Lists.newArrayList(sampleCommonSet))

        mockServer.expect(requestTo(GET_ALL_CARDSETS))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(JSONCardSetArray));
        when:
        def result = mtgIORestTemplate.getCardSetsArrrayOfAllCardSets()
        then:
        mockServer.verify()
        result == expectedCardSetsArray
    }

    def "getCardSetsArrrayOfAllCardSets should return empty CardSetsArray with log.info"() {
        given:
        mockServer.expect(requestTo(GET_ALL_CARDSETS))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getJSONCardSetArrayFromCardSets()));
        when:
        def result = mtgIORestTemplate.getCardSetsArrrayOfAllCardSets()
        then:
        mockServer.verify()
        result.getSets().isEmpty()
        logCaptor.getLogs("info").contains(String.format("%s answered with empty list", GET_ALL_CARDSETS))
    }

    def "getCardSetsArrayByCardSetsNames should return and deserialize array of sampleCommonSet and sampleMasterSet"() {
        given:
        def searchedCardSets = HashSet.of(sampleCommonSet.name, sampleMastersSet.name)
        def JSONCardSetArray = getJSONCardSetArrayFromCardSets(sampleCommonSet, sampleMastersSet)
        def expectedCardSets = new CardSetsArray(Lists.newArrayList(sampleCommonSet,sampleMastersSet));

        mockServer.expect(requestTo(GET_SETS_BY_NAMES_URL + encodeURLQuery(sampleCommonSet.name, sampleMastersSet.name)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(JSONCardSetArray));
        when:
        def result = mtgIORestTemplate.getCardSetsArrayByCardSetsNames(searchedCardSets)
        then:
        mockServer.verify()
        result == expectedCardSets
    }

    def encodeURLQuery(String... setNames) {
        def setNamesAsSingleString = Arrays.asList(setNames)
                .reverse()
                .stream()
                .collect(Collectors.joining("|"))
        return URLEncoder.encode(setNamesAsSingleString, StandardCharsets.UTF_8.toString())
    }

    def getJSONCardSetArrayFromCardSets(CardSet... sets) {
        return objectMapper.writeValueAsString(new CardSetsArray(Arrays.asList(sets)))
    }

}
