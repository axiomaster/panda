package com.axiomaster.lexer;

import com.axiomaster.lexer.token.Token;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void lex() {
        Lexer l = new Lexer(new InputStreamReader(System.in));
        l.lex();

        Queue<Token> tokens = l.getTokens();
        while (tokens.size() > 0) {
            Token t = tokens.poll();
            System.out.println(t);
        }
    }
}