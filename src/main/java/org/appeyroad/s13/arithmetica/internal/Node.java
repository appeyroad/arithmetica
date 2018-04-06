package org.appeyroad.s13.arithmetica.internal;

public abstract class Node<T extends Node, U extends Node> {

    private T left;
    private U right;

    public void setLeft(final T left) {
        this.left = left;
    }

    public void setRight(final U right) {
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public U getRight() {
        return right;
    }

    abstract public int evaluate();
}
