package com.brzn.box_eval.cache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardCacheFacade {
    private final CardCacheCommand command;

    public void updateCache(){
        command.update();
    }
}
