package com.axiomaster.lexer.token;

public class StrToken extends Token {

    private final static String TOKEN_TYPE = "String";

    private String literal;

    public StrToken(int lineNo, String str) {
        super(lineNo, str);
        literal = str;
        this.tokenType = TOKEN_TYPE;
    }

    @Override
    public boolean isString() {
        return true;
    }
}
