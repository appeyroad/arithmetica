package org.appeyroad.s13.arithmetica.internal;

interface Operator {

    int getPrecedence();

    long evaluate(final long a, final long b);

    String getSymbol();
}
