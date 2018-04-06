package org.appeyroad.s13.arithmetica.internal;

final class NumberNode extends Node<OperatorNode, OperatorNode> {

    private long val;

    NumberNode(long val) {
        this.val = val;
    }

    long getValue() {
        return val;
    }

    void setValue(long val) {
        this.val = val;
    }

    @Override
    long evaluate() {
        return val;
    }

    @Override
    public String toString() {
        return Long.toString(val);
    }
}
