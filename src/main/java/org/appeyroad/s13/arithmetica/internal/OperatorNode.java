package org.appeyroad.s13.arithmetica.internal;

final class OperatorNode extends Node<Node, Node> {

    private static final String NULL = "";

    private final OperatorType op;

    OperatorNode(OperatorType op) {
        this.op = op;
    }

    int getPrecedence() {
        return op.getPrecedence();
    }

    @Override
    void setRight(Node right) {
        if (getLeft() == null) {
            super.setLeft(right);
        }
        super.setRight(right);
    }

    @Override
    public long evaluate() {
        final Node l = getLeft();
        final Node r = getRight();
        if (r == null) {
            return l.evaluate();
        }
        return op.evaluate(l.evaluate(), r.evaluate());
    }

    @Override
    public String toString() {
        final Node l = getLeft();
        final Node r = getRight();
        final String sym = op.getSymbol();
        return String.format(
                "%s %s %s",
                l.toString(),
                sym,
                r == null ? NULL : r.toString()
        );
    }
}
