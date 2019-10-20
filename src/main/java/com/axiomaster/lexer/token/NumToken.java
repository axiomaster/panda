package com.axiomaster.lexer.token;

public class NumToken extends Token {

    private final static String TOKEN_TYPE = "Number";

    private int value;

    public NumToken(int lineNo, String text) {
        super(lineNo, text);
        this.value = Integer.parseInt(text);
        this.tokenType = TOKEN_TYPE;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public int getNumber() {
        return value;
    }
}
