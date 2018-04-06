package org.appeyroad.s13.arithmetica;

public final class Tree {

    private Node root = null;

    public boolean add(final String s) {
        switch (s) {
            case "+":
                add(new OperatorNode(Operators.ADD));
                return true;
            case "-":
                add(new OperatorNode(Operators.SUB));
                return true;
            case "/":
                add(new OperatorNode(Operators.DIV));
                return true;
            case "*":
                add(new OperatorNode(Operators.MUL));
                return true;
        }
        try {
            final int n = Integer.parseInt(s);
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
            final int val = p.getValue() * 10 + ((NumberNode) node).getValue();
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

    int evaluate() {
        return root == null ? 0 : root.evaluate();
    }

    @Override
    public String toString() {
        return root == null ? "NULL" : root.toString();
    }
}
