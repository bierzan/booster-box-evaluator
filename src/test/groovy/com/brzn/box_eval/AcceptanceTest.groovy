package com.brzn.box_eval

import com.brzn.box_eval.box.domain.BoxFacade
import com.brzn.box_eval.box.domain.SampleBoxes
import com.brzn.box_eval.evaluation.domain.EvaluationFacade
import com.brzn.box_eval.evaluation.domain.SampleEvaluation
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceTest extends IntegrationTest implements SampleBoxes, SampleEvaluation {

    @Autowired
    private BoxFacade boxFacade

    @Autowired
    private EvaluationFacade evaluationFacade

    def "should get last evaluation"() {
        given: 'inventory with SampleBox and SampleEvaluation parameters'
        when: 'I go to /evaluation/box?setName={setName}'
        ResultActions getEvaluation = mockMvc.perform(get("/evaluation/box?setName=" + sampleBox.cardSetName))

        then: 'I see I got status 200 and evaluation refers to given card set'
        getEvaluation
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.cardSetId').value(sampleBox.id))

        then: 'I see it is not todays evaluation'
        def date1 = getLocalDateFromJson(getEvaluation, 'date');
        date1 < LocalDate.now();
    }

    def LocalDate getLocalDateFromJson(ResultActions resultActions, String key) {
        def date = LocalDate.parse JsonPath.read(resultActions.andReturn().getResponse().getContentAsString(), '$.' + key);
        return date;
    }
}
