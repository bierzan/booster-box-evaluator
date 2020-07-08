package com.brzn.box_eval.mtg_io_client

import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.dto.CardSet
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.Lists
import io.vavr.collection.HashSet
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
    private RestTemplate restTemplate = new RestTemplate()
    private MockRestServiceServer mockServer;
    MtgIORestTemplate mtgIORestTemplate = new MtgIORestTemplate(restTemplate);
    private ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().simpleDateFormat("yyyy-MM-dd").build()

    def setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    def "should return and deserialize sampleSet"() {
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

    def "should return and deserialize array of sampleCommonSet and sampleMasterSet"() {
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

    private String encodeURLQuery(String... setNames) {
        def setNamesAsSingleString = Arrays.asList(setNames)
                .reverse()
                .stream()
                .collect(Collectors.joining("|"))
        return URLEncoder.encode(setNamesAsSingleString, StandardCharsets.UTF_8.toString())
    }

    private String getJSONCardSetArrayFromCardSets(CardSet... sets) {
        return objectMapper.writeValueAsString(new CardSetsArray(Arrays.asList(sets)))
    }

}