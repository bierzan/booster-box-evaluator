package com.brzn.box_eval

import com.brzn.box_eval.box.domain.BoxFacade
import com.brzn.box_eval.box.domain.SampleBoxes
import com.brzn.box_eval.evaluation.domain.EvaluationFacade
import com.brzn.box_eval.evaluation.domain.SampleEvaluation
import com.brzn.box_eval.mtg_io_client.domain.MtgIOClient
import com.brzn.box_eval.scryfall_client.domain.ScryfallClient
import com.jayway.jsonpath.JsonPath
import io.vavr.collection.List
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

    def mtgIOClient = Mock(MtgIOClient);
    def scryfallClient = Mock(ScryfallClient);

    def "should get evaluation for recently released Box"() {

        given: 'inventory with OldBox and recentBoxes recieved from external Api'
        boxFacade.add(oldBox);
        def recentBoxes = [lastWeekBox, todaysBox] as List;

        when: 'I invoke findLast'
        def lastBox = boxFacade.findLast()

        then: 'I see OldBox'
        lastBox == oldBox;

        when: 'I save recently released Boxes and invoke findLast'
        boxFacade.add(recentBoxes)
        def newLastBox = boxFacade.findLast();

        then: 'I see that todayBox is now the last one'
        newLastBox == todaysBox;
    }

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
        def date1 = getLocalDateFromJson(getEvaluation, 'date');
        date1 < LocalDate.now();
    }

    def LocalDate getLocalDateFromJson(ResultActions resultActions, String key) {
        def date = LocalDate.parse JsonPath.read(resultActions.andReturn().getResponse().getContentAsString(), '$.' + key);
        return date;
    }
}
