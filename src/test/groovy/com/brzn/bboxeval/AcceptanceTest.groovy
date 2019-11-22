package com.brzn.bboxeval

import com.brzn.bboxeval.box.domain.BoxFacade
import com.brzn.bboxeval.box.domain.SampleBox
import com.brzn.bboxeval.evaluation.domain.EvaluationFacade
import com.brzn.bboxeval.evaluation.domain.SampleEvaluation
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceTest extends IntegrationTest implements SampleBox, SampleEvaluation {

    @Autowired
    BoxFacade boxFacade

    @Autowired
    EvaluationFacade evaluationFacade

    def "should get last evaluation"() {
        given: 'inventory with SampleBox and SampleEvaluation parameters'
        boxFacade.add(sampleBox)
        sampleEvaluation.cardSetId = sampleBox.id
        evaluationFacade.add(sampleEvaluation)

        when: 'I go to /evaluation/box?setName={setName}'
        ResultActions getEvaluation = mockMvc.perform(get("/evaluation/box?setName=" + sampleBox.cardSetName))

        then: 'I see I got status 200 and evaluation refers to given card set'
        getEvaluation
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.cardSetId').value(sampleBox.id))

        then: 'I see it is not todays evaluation'
        def ld = LocalDate.parse(JsonPath.read(getEvaluation.andReturn().getResponse().getContentAsString(), '$.date'))
        ld.isBefore(LocalDate.now());

        when: "I post to /evaluation/calculate/box?setName={setName}"
        then: "I see I got new Evaluation"

        when: 'I go to /evaluation/box?setName={setName}'
        then: 'I see I got todays Evaluation'
    }
}
