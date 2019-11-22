package com.brzn.bboxeval.box.exception;

public class BoxNotFoundException extends RuntimeException {

    public BoxNotFoundException(String cardSetName) {
        super(String.format("Box with name %s not found",cardSetName));
    }
}
