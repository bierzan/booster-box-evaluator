package com.brzn.bboxeval

import com.brzn.bboxeval.box.domain.BoxFacade
import com.brzn.bboxeval.box.domain.SampleBoxes
import com.brzn.bboxeval.evaluation.domain.EvaluationFacade
import com.brzn.bboxeval.evaluation.domain.SampleEvaluation
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceTest extends IntegrationTest implements SampleBoxes, SampleEvaluation {

    @Autowired
    BoxFacade boxFacade

    @Autowired
    EvaluationFacade evaluationFacade

    def "should get evaluation for recently released Box"() {

        given: 'inventory with OldBox'
        boxFacade.add(oldBox);

        when: 'I invoke findLast'
        def lastBox = boxFacade.findLast()

        then: 'I see OldBox'
        lastBox == oldBox;

        when: 'I invoke searchNew'
        def newBoxes = boxFacade.searchNew();

        then: 'I see that NewBoxes were found'
        newBoxes == [lastWeekBox, todaysBox] as List;
    }

//    def "should get last evaluation"() {
//        given: 'inventory with SampleBox and SampleEvaluation parameters'
//        boxFacade.add(sampleBox)
//        sampleEvaluation.cardSetId = sampleBox.id
//        evaluationFacade.add(sampleEvaluation)
//
//        when: 'I go to /evaluation/box?setName={setName}'
//        ResultActions getEvaluation = mockMvc.perform(get("/evaluation/box?setName=" + sampleBox.cardSetName))
//
//        then: 'I see I got status 200 and evaluation refers to given card set'
//        getEvaluation
//                .andExpect(status().isOk())
//                .andExpect(jsonPath('$.cardSetId').value(sampleBox.id))
//
//        then: 'I see it is not todays evaluation'
//        def date1 = getLocalDateFromJson(getEvaluation, 'date');
//        date1 < LocalDate.now();
//
//        when: "I post to /evaluation/calculate/box?setName={setName}"
//        ResultActions calculate = mockMvc.perform(post("/evaluation/calculate/box?setName=" + sampleBox.cardSetName))
//
//        then: "I see I got new Evaluation with current date and avgValue calculated"
//        calculate
//                .andExpect(status().isOk())
//                .andExpect(jsonPath('$.avgValue').value(BigDecimal.TEN))
//        def date2 = getLocalDateFromJson(calculate, 'date');
//        date2 == LocalDate.now();
//
//        when: 'I go to /evaluation/box?setName={setName}'
//        then: 'I see I got todays Evaluation'
//    }

    def LocalDate getLocalDateFromJson(ResultActions resultActions, String key) {
        def date = LocalDate.parse JsonPath.read(resultActions.andReturn().getResponse().getContentAsString(), '$.' + key);
        return date;
    }
}
