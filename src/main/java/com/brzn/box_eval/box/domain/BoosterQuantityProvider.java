package com.brzn.box_eval.box.domain;

import com.brzn.box_eval.box.dto.BoxCardSetType;

public class BoosterQuantityProvider {

    public int getBoosterQuantity(Box box) {
        return ("Conspiracy").equals(box.getCardSetBlock()) || BoxCardSetType.MASTERS.equals(box.getBoxCardSetType()) ?
                24 : 36;
    }
}