package com.brzn.box_eval.mtg_io_client


import com.brzn.box_eval.mtgIOclient.domain.SampleCardSets
import com.brzn.box_eval.mtg_io_client.dto.CardSetsArray
import com.fasterxml.jackson.databind.ObjectMapper
import io.vavr.collection.List
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus

class MtgIOClientTest extends Specification implements SampleCardSets {
    private RestTemplate restTemplate = new RestTemplate()
    private MockRestServiceServer mockServer;
    MtgIOClient client = new MtgIOClient(restTemplate);
    private ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().simpleDateFormat("yyyy-MM-dd").build()

    def setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    def "should return and deserialize sampleSet"() { //todo do refactoru
        given:
        def names = List.of("khans2").toSet()
        mockServer.expect(requestTo("https://api.magicthegathering.io/v1/sets?name=khans"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(json2));
        def expected = objectMapper.readValue(json2, CardSetsArray.class);
        when:
        def wynik = client.findCardSetsByName(names)
        then:
        mockServer.verify()
        wynik.toJavaList() == expected.getSets()
    }

    def json2 = objectMapper.writeValueAsString(new CardSetsArray(java.util.List.of(sampleCommonSet)))

    def json = '{\n' +
            '   "sets":[\n' +
            '      {\n' +
            '         "code":"KTK",\n' +
            '         "name":"Khans of Tarkir",\n' +
            '         "type":"expansion",\n' +
            '         "booster":[\n' +
            '            [\n' +
            '               "rare",\n' +
            '               "mythic rare"\n' +
            '            ],\n' +
            '            "uncommon",\n' +
            '            "uncommon",\n' +
            '            "uncommon",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "common",\n' +
            '            "land",\n' +
            '            "marketing"\n' +
            '         ],\n' +
            '         "releaseDate":"2014-09-26",\n' +
            '         "block":"Khans of Tarkir",\n' +
            '         "onlineOnly":false\n' +
            '      },\n' +
            '      {\n' +
            '         "code":"PKTK",\n' +
            '         "name":"Khans of Tarkir Promos",\n' +
            '         "type":"promo",\n' +
            '         "releaseDate":"2014-09-27",\n' +
            '         "block":"Khans of Tarkir",\n' +
            '         "onlineOnly":false\n' +
            '      }\n' +
            '   ]\n' +
            '}'
}
