package com.axiomaster.exception;

public class LexerException extends PandaException {
    public LexerException(String m) {
        super(m);
    }

    public LexerException(Exception e) {
        super(e);
    }
}
