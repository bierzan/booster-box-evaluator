package com.brzn.box_eval.card.port;

import java.io.File;
import java.time.LocalDate;

public interface CardJsonFileProvider {

    File getCardsJsonFileReleasedAfter(LocalDate lastCardUpdateDate);
}
