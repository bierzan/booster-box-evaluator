//package com.brzn.bboxeval.boxEval
//
//
//import org.spockframework.spring.SpringBean
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.test.web.servlet.MockMvc
//import spock.lang.Specification
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
//
//
//@AutoConfigureMockMvc
//@WebMvcTest
//class BoxEvalControllerTest extends Specification {
//
//    private static final GET_EVALUATION = "/box/evaluation/"
//
//    @SpringBean
//    private BoxEvalService boxService = Mock();
//
//    @Autowired
//    private MockMvc mvc;
//
//    def "when getBoxEvaluation is performed with proper cardSetCode then the response has status 200 and content is BoxEvaluator"() {
//        given:
//        String cardSetCode = "properSetName";
//        BoxEvaluation boxEvaluation = new BoxEvaluation(cardSetCode);
//        boxService.getBoxEvaluation(_ as String) >> Optional.of(boxEvaluation);
//
//        when:
//        def result = mvc.perform(get(GET_EVALUATION + cardSetCode));
//
//        then:
//        result.andExpect(status().isOk());
//        result.andExpect(jsonPath('$.cardSetCode').value(cardSetCode));
//    }
//
//    def "when getBoxEvaluation is performed with wrong cardSetCode then the response has status 404"() {
//        given:
//        String cardSetCode = "wrongSetName";
//        boxService.getBoxEvaluation(_ as String) >> Optional.empty();
//
//        when:
//        def result = mvc.perform(get(GET_EVALUATION + cardSetCode));
//
//        then:
//        result.andExpect(status().isNotFound());
//    }
//}
