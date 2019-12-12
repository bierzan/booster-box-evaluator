package com.brzn.bboxeval.box.domain

import com.brzn.bboxeval.box.dto.BoxDto
import groovy.transform.CompileStatic

import java.time.LocalDate

@CompileStatic
trait OldBox {
    BoxDto oldBox = createBoxDto();

    static private BoxDto createBoxDto() {
        return BoxDto.builder()
                .releaseDate(LocalDate.MIN)
                .build()
    }
}


