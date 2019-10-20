package com.axiomaster.lexer;

import com.axiomaster.exception.LexerException;
import com.axiomaster.lexer.token.IdToke;
import com.axiomaster.lexer.token.NumToken;
import com.axiomaster.lexer.token.StrToken;
import com.axiomaster.lexer.token.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public final static String regexPat = ""
            // 空白 注释
            + "\\s*((//.*)"
            // 数字
            + "|([0-9]+)"
            // 字符串字面量
            + "|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            // 标识符
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);

    private Queue<Token> tokenList = new LinkedList<Token>();

    private LineNumberReader reader;

    public Lexer(Reader r) {
        reader = new LineNumberReader(r);
    }

    public void lex() {
        String line;
        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new LexerException(e);
            }

            if (line == null) {
                break;
            }

            int lineNo = reader.getLineNumber();

            lexLine(line, lineNo);
        }
    }

    public Queue<Token> getTokens() {
        return tokenList;
    }


    private void lexLine(String line, int lineNo) throws LexerException {
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);

        int startPos = 0;
        int endPos = line.length();

        while (startPos < endPos) {
            matcher.region(startPos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                startPos = matcher.end();
            } else {
                throw new LexerException("invalid token at line:" + lineNo + ", position:" + startPos);
            }
        }
        tokenList.add(new IdToke(lineNo, Token.EOL));
    }

    private void addToken(int lineNo, Matcher matcher) {
        String m = matcher.group(1);
        // not space
        if (m != null) {
            // not comment
            if (matcher.group(2) == null) {
                Token token;
                if (matcher.group(3) != null) {
                    token = new NumToken(lineNo, m);
                } else if (matcher.group(4) != null) {
                    token = new StrToken(lineNo, toStringLiteral(m));
                } else {
                    token = new IdToke(lineNo, m);
                }

                tokenList.add(token);
            }
        }
    }

    private String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
