package org.appeyroad.s13.arithmetica;

public final class OperatorNode extends Node<Node, Node> {

    private final Operators op;

    public OperatorNode(Operators op) {
        this.op = op;
    }

    public Operators getOperator() {
        return op;
    }

    public int getPrecedence() {
        return op.getPrecedence();
    }

    @Override
    public int evaluate() {
        final Node l = getLeft();
        final Node r = getRight();
        final int a = l == null ? 0 : l.evaluate();
        final int b = r == null ? 0 : r.evaluate();
        return op.evaluate(a, b);
    }

    @Override
    public String toString() {
        return op.name();
    }
}