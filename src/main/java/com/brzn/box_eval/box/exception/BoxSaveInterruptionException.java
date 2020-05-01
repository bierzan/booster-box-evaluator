package com.brzn.box_eval.box.exception;

import com.brzn.box_eval.box.dto.BoxDto;
import io.vavr.control.Option;

public class BoxSaveInterruptionException extends RuntimeException {
    public BoxSaveInterruptionException(BoxDto boxDto) {
        Option.of(boxDto)
                .map(dto -> new BoxSaveInterruptionException(String.format("Box saving proccess interrupted. Box %s not saved", dto.toString())))
                .getOrElseThrow(() -> new IllegalArgumentException("BoxDto can't be null"));
    }

    public BoxSaveInterruptionException(String message) {
        super(message);
    }
}
