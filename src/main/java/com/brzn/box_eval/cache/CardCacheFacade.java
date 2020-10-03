package com.brzn.box_eval.cache;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardCacheFacade {
    private final CardCacheUpdater command;

    public void updateCache(){
        command.update();
    }
}
