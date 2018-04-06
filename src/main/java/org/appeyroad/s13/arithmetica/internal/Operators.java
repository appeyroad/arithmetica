package org.appeyroad.s13.arithmetica.internal;

enum Operators implements Operator {

    ADD {

        @Override
        public int getPrecedence() {
            return 1;
        }

        @Override
        public long evaluate(long a, long b) {
            return a + b;
        }

        @Override
        public String getSymbol() {
            return "+";
        }
    },

    SUB {

        @Override
        public int getPrecedence() {
            return 1;
        }

        @Override
        public long evaluate(long a, long b) {
            return a - b;
        }

        @Override
        public String getSymbol() {
            return "-";
        }
    },

    MUL {

        @Override
        public int getPrecedence() {
            return 0;
        }

        @Override
        public long evaluate(long a, long b) {
            return a * b;
        }

        @Override
        public String getSymbol() {
            return "*";
        }
    },

    DIV {

        @Override
        public int getPrecedence() {
            return 0;
        }

        @Override
        public long evaluate(long a, long b) {
            return a / b;
        }

        @Override
        public String getSymbol() {
            return "/";
        }
    }
}
