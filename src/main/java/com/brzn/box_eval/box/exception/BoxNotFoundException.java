package com.brzn.box_eval.box.exception;

public class BoxNotFoundException extends RuntimeException {

    public BoxNotFoundException(String cardSetName) {
        super(String.format("Box with name %s not found",cardSetName));
    }

    public BoxNotFoundException() {
        super("Box not found");
    }
}
