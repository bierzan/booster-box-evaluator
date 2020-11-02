package com.brzn.box_eval.card.domain;

import lombok.Value;

import java.io.File;

@Value(staticConstructor = "of")
public class CardsDataFile {
    File file;
}
