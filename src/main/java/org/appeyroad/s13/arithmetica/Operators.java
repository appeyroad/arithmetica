package org.appeyroad.s13.arithmetica;

public enum Operators implements Operator {

    ADD {

        @Override
        public int getPrecedence() {
            return 1;
        }

        @Override
        public int evaluate(int a, int b) {
            return a + b;
        }
    },

    SUB {

        @Override
        public int getPrecedence() {
            return 1;
        }

        @Override
        public int evaluate(int a, int b) {
            return a - b;
        }
    },

    MUL {

        @Override
        public int getPrecedence() {
            return 0;
        }

        @Override
        public int evaluate(int a, int b) {
            return a * b;
        }
    },

    DIV {

        @Override
        public int getPrecedence() {
            return 0;
        }

        @Override
        public int evaluate(int a, int b) {
            return a / b;
        }
    }
}
