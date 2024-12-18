package com.bible_canvas.biblecanvas.init.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidParseException extends RuntimeException {
    private static final String MESSAGE = "Invalid parse expression";

    public InvalidParseException() {
        super(MESSAGE);
    }
}
