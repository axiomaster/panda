package com.axiomaster.exception;

import com.axiomaster.ast.ASTree;

public class PandaException extends RuntimeException {
    public PandaException(String m) {
        super(m);
    }

    public PandaException(Exception e) {
        super(e);
    }

    public PandaException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
