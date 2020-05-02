package com.brzn.box_eval.box.domain

import com.brzn.box_eval.box.dto.BoxDto
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleBoxes {
    BoxDto sampleBox = BoxDto.builder()
            .cardSetName("SampleSetName")
            .boosterStructure(createBoosterStructure())
            .build();

    BoxDto oldBoxDto = BoxDto.builder()
            .cardSetName("oldSet")
            .releaseDate(LocalDate.MIN)
            .build();

    Box oldBox = Box.builder()
            .cardSetName("oldSet")
            .releaseDate(LocalDate.MIN)
            .build();

    BoxDto lastWeekBox = BoxDto.builder()
            .cardSetName("lastWeekSet")
            .releaseDate(LocalDate.now().minusWeeks(1))
            .build();

    BoxDto todaysBox = BoxDto.builder()
            .cardSetName("todaySet")
            .releaseDate(LocalDate.now())
            .build();

    static private HashMap createBoosterStructure() {
        Map<String, Integer> boosterStructure = new HashMap<>();
        boosterStructure.put("rareMythic", 1)
        boosterStructure.put("uncommon", 2)
        boosterStructure.put("common", 3)
        boosterStructure.put("land", 1)
        return boosterStructure;
    }
}


