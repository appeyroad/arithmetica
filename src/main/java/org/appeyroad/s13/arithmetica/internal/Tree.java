package org.appeyroad.s13.arithmetica.internal;

public final class Tree {

    private Node root = null;

    public boolean add(final String s) {
        switch (s) {
            case "+":
                return add(new OperatorNode(OperatorType.ADD));
            case "-":
                return add(new OperatorNode(OperatorType.SUB));
            case "/":
                return add(new OperatorNode(OperatorType.DIV));
            case "*":
                return add(new OperatorNode(OperatorType.MUL));
        }
        try {
            final long n = Long.parseLong(s);
            return add(new NumberNode(n));
        } catch (final NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean add(final Node node) {
        if (root == null) {
            root = node;
            return true;
        }

        if (node instanceof NumberNode) {
            return addNumber((NumberNode) node);
        } else if (node instanceof OperatorNode) {
            return addOperator((OperatorNode) node);
        }
        return false;
    }

    private boolean addNumber(final NumberNode node) {
        final Node parent = getRightmostNode();
        if (parent instanceof OperatorNode) {
            ((OperatorNode) parent).setRight(node);
            return true;
        } else if (parent instanceof NumberNode) {
            final NumberNode p = ((NumberNode) parent);
            final String oldVal = String.valueOf(p.getValue());
            final String in = String.valueOf(((NumberNode) node).getValue());
            final String newVal = oldVal + in;
            final long val = Long.parseLong(newVal);
            p.setValue(val);
            return true;
        }
        return false;
    }

    private boolean addOperator(final OperatorNode node) {
        Node previous = null;
        Node current = root;
        while (current != null) {
            if (current instanceof NumberNode) {
                if (current == root) {
                    root = node;
                } else if (previous instanceof OperatorNode) {
                    ((OperatorNode) previous).setRight(node);
                }
                node.setLeft(current);
                return true;
            }

            if (current instanceof OperatorNode) {
                final int thisP = node.getPrecedence();
                final int otherP = ((OperatorNode) current).getPrecedence();

                if (thisP >= otherP) {
                    if (current.getRight() == null) {
                        return false;
                    }

                    if (current == root) {
                        root = node;
                    } else if (previous instanceof OperatorNode) {
                        ((OperatorNode) previous).setRight(node);
                    }
                    node.setLeft(current);
                    return true;
                }
            }

            previous = current;
            current = current.getRight();
        }
        return false;
    }

    public void delete() {
        Node previous = null;
        Node current = root;
        while (current != null) {
            if (current.getRight() == null) {
                if (current == root) {
                    root = current.getLeft();
                } else if (previous instanceof OperatorNode) {
                    ((OperatorNode) previous).setRight(current.getLeft());
                }
            }
            previous = current;
            current = current.getRight();
        }
    }

    public void clear() {
        root = null;
    }

    private Node getRightmostNode() {
        Node current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    public long evaluate() {
        return root == null ? 0 : root.evaluate();
    }

    @Override
    public String toString() {
        return root == null ? "" : root.toString();
    }
}
