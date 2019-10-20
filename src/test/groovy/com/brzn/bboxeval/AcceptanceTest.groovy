package com.brzn.bboxeval

import com.brzn.bboxeval.box.domain.BoxFacade
import com.brzn.bboxeval.box.domain.SampleBox
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceTest extends IntegrationTest implements SampleBox {

    @Autowired
    BoxFacade boxFacade;

    def "positive evaluation scenario"() {
        given: 'inventory with Sample Box parameters'
        boxFacade.add(sampleBox)

        when: 'I go to /evaluation/box/{setName}'
        ResultActions getEvaluation = mockMvc.perform(get("/evaluate/box/" + sampleBox.cardSetName))

        then: 'I see chosen set booster box evaluation'
        getEvaluation.andExpect(status().isOk())
                .andExpect(jsonPath('$.avgValue').value(0))

    }
}
