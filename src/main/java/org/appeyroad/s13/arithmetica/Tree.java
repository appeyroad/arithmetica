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
            add(new NumberNode(n));
            return true;
        } catch (final NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void add(final Node node) {
        if (root == null) {
            root = node;
            return;
        }

        if (node instanceof NumberNode) {
            addNumber((NumberNode) node);
        } else if (node instanceof OperatorNode) {
            addOperator((OperatorNode) node);
        }
    }

    private void addNumber(final NumberNode node) {
        final Node parent = getRightmostNode();
        if (parent instanceof OperatorNode) {
            ((OperatorNode) parent).setRight(node);
        } else if (parent instanceof NumberNode) {
            final NumberNode p = ((NumberNode) parent);
            final int val = p.getValue() * 10 + ((NumberNode) node).getValue();
            p.setValue(val);
        }
    }

    private void addOperator(final OperatorNode node) {
        Node previous = null;
        Node current = root;
        while (true) {
            if (current instanceof NumberNode) {
                if (current == root) {
                    root = node;
                } else if (previous instanceof OperatorNode) {
                    ((OperatorNode) previous).setRight(node);
                }
                node.setLeft(current);
                return;
            }

            if (current instanceof OperatorNode) {
                final int thisP = node.getPrecedence();
                final int otherP = ((OperatorNode) current).getPrecedence();

                if (thisP >= otherP) {
                    if (current == root) {
                        root = node;
                    } else if (previous instanceof OperatorNode) {
                        ((OperatorNode) previous).setRight(node);
                    }
                    node.setLeft(current);
                    return;
                }
            }

            previous = current;
            current = current.getRight();
        }
    }

    private Node getRightmostNode() {
        Node current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    int evaluate() {
        return root.evaluate();
    }
}
