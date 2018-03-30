package org.appeyroad.s13.arithmetica;

public final class NumberNode extends Node<OperatorNode, OperatorNode> {

    private final int val;

    public NumberNode(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
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
