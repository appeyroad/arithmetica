package org.appeyroad.s13.arithmetica;

public final class NumberNode extends Node<OperatorNode, OperatorNode> {

    private int val;

    public NumberNode(int val) {
        Arithmetica.require(
                0 <= val && val < 10,
                "Out of range: val < 0 || 9 < val"
        );
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    public void setValue(int val) {
        Arithmetica.require(
                val / 10 == this.val,
                "Out of range: oldVal / 10 != newVal"
        );
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
