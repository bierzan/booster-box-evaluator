package com.brzn.box_eval

import com.brzn.box_eval.box.domain.BoxFacade
import com.brzn.box_eval.box.domain.SampleBoxes
import com.brzn.box_eval.evaluation.domain.EvaluationFacade
import com.brzn.box_eval.evaluation.domain.SampleEvaluation
import com.brzn.box_eval.mtgIOclient.domain.SampleSets
import com.brzn.box_eval.mtg_io_client.domain.MtgIOClient
import com.brzn.box_eval.scryfall_client.domain.SampleCards
import com.brzn.box_eval.scryfall_client.domain.ScryfallClient
import com.jayway.jsonpath.JsonPath
import io.vavr.collection.List
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import java.time.LocalDate

class AcceptanceTest extends IntegrationTest implements SampleBoxes, SampleEvaluation, SampleSets, SampleCards {

    @Autowired
    BoxFacade boxFacade

    @InjectMocks
    EvaluationFacade evaluationFacade

    def mtgIOClient = Mock(MtgIOClient);
    def scryfallClient = Mock(ScryfallClient);


    def "should get evaluation for recently released Box"() {

        given: 'inventory with OldBox'
        boxFacade.add(oldBox);

        when: 'I invoke findLast'
        def lastBox = boxFacade.findLast()

        then: 'I see OldBox'
        lastBox == oldBox;

        when: 'I invoke searchNew and got 2 new Boxes from external source'
        newBoxes == [lastWeekBox, todaysBox] as List;

        then: 'I see that NewBoxes were found'
        //todo test do poprawyt
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
