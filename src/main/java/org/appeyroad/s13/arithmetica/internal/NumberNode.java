package org.appeyroad.s13.arithmetica.internal;

public final class NumberNode extends Node<OperatorNode, OperatorNode> {

    private int val;

    public NumberNode(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    public void setValue(int val) {
        this.val = val;
    }

    @Override
    public int evaluate() {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}
