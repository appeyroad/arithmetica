package org.appeyroad.s13.arithmetica.internal;

public interface Operator {

    int getPrecedence();

    int evaluate(final int a, final int b);

    String getSymbol();
}
