package com.brzn.bboxeval.box

import javassist.tools.web.BadHttpRequest
import org.mockito.InjectMocks
import org.mockito.Mock
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import spock.lang.Specification

import java.awt.HeadlessException

import static org.mockito.MockitoAnnotations.initMocks
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@AutoConfigureMockMvc
@WebMvcTest
class BoxControllerTest extends Specification {

    private static final GET_EVALUATION = "/box/evaluation/"

    @SpringBean
    private BoxService boxService = Mock();

    @Autowired
    private MockMvc mvc;

    def "when get is performed with proper setName then the response has status 200 and content is BoxEvaluator"() {
        given:
        String setName = "properSetName";
        BoxEvaluation boxEvaluation = new BoxEvaluation(setName);
        boxService.getBoxEvaluation(_ as String) >> boxEvaluation;

        when:
        def result = mvc.perform(get(GET_EVALUATION + setName));

        then:
        result.andExpect(status().isOk());
        result.andExpect(jsonPath('$.setName').value(setName));
    }

    def "when get is performed with wrong setName then the response has status 400"() {
        given:
        String setName = "wrongSetName";
        boxService.getBoxEvaluation(_ as String) >> {throw new BadHttpRequest()};

        when:
        def result = mvc.perform(get(GET_EVALUATION + setName));

        then:
        result.andExpect(status().isBadRequest());
    }
}
