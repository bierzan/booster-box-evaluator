package com.brzn.box_eval.box.domain


import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait SampleBoxes {

    Box oldBox = Box.builder()
            .cardSetName("oldSet")
            .releaseDate(LocalDate.MIN)
            .build();

    Box todayBox = Box.builder()
            .cardSetName("todaySet")
            .releaseDate(LocalDate.now())
            .build();

}


