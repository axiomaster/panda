package com.axiomaster.lexer.token;

public class IdToke extends Token {

    private final static String TOKEN_TYPE = "Identifier";

    public IdToke(int lineNum, String id) {
        super(lineNum, id);
        tokenType = TOKEN_TYPE;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }
}
