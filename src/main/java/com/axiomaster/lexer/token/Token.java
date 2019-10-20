package com.axiomaster.lexer.token;

import com.axiomaster.exception.LexerException;

public class Token {
    public static final Token EOF = new Token(-1, "");

    public static final String EOL = "\\n";

    private int lineNo;
    private String text;
    protected String tokenType;

    public Token(int lineNo, String text) {
        this.lineNo = lineNo;
        this.text = text;
    }

    public boolean isIdentifier() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public int getNumber() {
        throw new LexerException("not number token");
    }

    public int getLineNo() {
        return lineNo;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return this.tokenType + ", lineNo:" + lineNo + ", token:" + text;
    }
}
