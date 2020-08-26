package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxCardSetType;
import com.brzn.box_eval.box.dto.BoxDto;
import com.brzn.box_eval.box.vo.BoosterSchema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Builder
class Box { //todo rozbic na encje + i encje bazodanowa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardSetName;
    private String cardSetCode;
    private BoxCardSetType boxCardSetType;
    private LocalDate releaseDate;
    private String type;
    private BoosterSchema boosterSchema;
    private int boosterQuantity;

    BoxDto dto() {
        return BoxDto.builder()
                .id(id)
                .cardSetName(cardSetName)
                .boosterSchema(boosterSchema)
                .releaseDate(releaseDate)
                .build();
    }

}
