package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.Booster;
import com.brzn.box_eval.box.dto.BoxDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

@Data
@Builder
class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String cardSetName;
    private String cardSetCode;
    private LocalDate releaseDate;
    private String type;
    private Booster booster;
    private short boosterQuantity;

    BoxDto dto(){
        Gson gson = new Gson();
        Type boosterStructureType = new TypeToken<HashMap<String, Integer>>(){}.getType();
        return BoxDto.builder()
                .id(id)
                .cardSetName(cardSetName)
                .booster(booster)
                .releaseDate(releaseDate)
                .build();
    }

}
