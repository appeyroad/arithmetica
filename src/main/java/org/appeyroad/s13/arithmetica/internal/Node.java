package org.appeyroad.s13.arithmetica.internal;

abstract class Node<T extends Node, U extends Node> {

    private T left;
    private U right;

    void setLeft(final T left) {
        this.left = left;
    }

    void setRight(final U right) {
        this.right = right;
    }

    T getLeft() {
        return left;
    }

    U getRight() {
        return right;
    }

    abstract long evaluate();
}
