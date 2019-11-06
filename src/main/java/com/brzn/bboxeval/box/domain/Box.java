package com.brzn.bboxeval.box.domain;

import com.brzn.bboxeval.box.dto.BoxDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;

@Entity
@Table(name = "booster_box")
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
    private String booster;
    private short boosterQuantity;

    BoxDto dto(){
        Gson gson = new Gson();
        Type boosterStructureType = new TypeToken<HashMap<String, Integer>>(){}.getType();
        return BoxDto.builder()
                .cardSetName(cardSetName)
                .boosterStructure(gson.fromJson(booster, boosterStructureType))
                .build();
    }
}