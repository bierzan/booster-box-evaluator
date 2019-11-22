package com.brzn.bboxeval.box.domain

import com.brzn.bboxeval.box.dto.BoxDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleBox {
    BoxDto sampleBox = createBoxDto("SampleSetName", createBoosterStructure(1, 2, 3, 1));

    static private BoxDto createBoxDto(String cardSetName, HashMap structure) {
        return BoxDto.builder()
                .id(1)
                .cardSetName(cardSetName)
                .boosterStructure(structure)
                .build()
    }

    static private HashMap createBoosterStructure(int rareMythic,
                                              int uncommon,
                                              int common,
                                              int land) {
        Map<String, Integer> boosterStructure = new HashMap<>();
        boosterStructure.put("rareMythic", rareMythic)
        boosterStructure.put("uncommon", uncommon)
        boosterStructure.put("common", common)
        boosterStructure.put("land", land)
        return boosterStructure;
    }
}


