package com.brzn.bboxeval.box.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Builder
@Getter
@Setter
public class BoxDto {
    private final long id;
    private final String cardSetName;
    private final HashMap boosterStructure;
}
