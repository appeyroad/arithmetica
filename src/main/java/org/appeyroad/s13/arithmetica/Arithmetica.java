package org.appeyroad.s13.arithmetica;

import org.appeyroad.s13.arithmetica.internal.Tree;

@SuppressWarnings("WeakerAccess")
public final class Arithmetica {

    private final Tree t;

    public Arithmetica(final String... ss) {
        t = new Tree();
        for (final String s : ss) {
            t.add(s);
        }
    }

    public boolean add(final String s) {
        return t.add(s);
    }

    public void delete() {
        t.delete();
    }

    public void clear() {
        t.clear();
    }

    public long evaluate() {
        return t.evaluate();
    }

    public String formula() {
        return t.toString();
    }

    public static void main(String... args) {
        final String formula = "1 + 2 * 3 / 4 - 5 + 6 + 78";
        final String[] inputSequence = formula.replace(" ", "").split("");
        final Arithmetica arithmetica = new Arithmetica(inputSequence);
        System.out.printf("%s = %d\n", arithmetica.formula(), arithmetica.evaluate());
        // Output: 1 + 2 * 3 / 4 - 5 + 6 + 78 = 81
        require(
                (1 + 2 * 3 / 4 - 5 + 6 + 78) == arithmetica.evaluate(),
                "Wrong value: " + arithmetica.evaluate()
        );
        require(
                formula.equals(String.join(" ", arithmetica.formula())),
                "Wrong formula: " + arithmetica.formula()
        );

        final Arithmetica initial = new Arithmetica();
        System.out.printf("%s = %d\n", initial.formula(), initial.evaluate());
        // Output: NULL = 0
        require(
                0 == initial.evaluate(),
                "Wrong initial value: " + initial.evaluate()
        );

        final Arithmetica illFormed = new Arithmetica("1 + + 23".split(" "));
        System.out.printf("%s = %d\n", illFormed.formula(), illFormed.evaluate());
        // Output: 1 + 23 = 24
        require(
                1 + 23 == illFormed.evaluate(),
                "Wrong initial value: " + illFormed.evaluate()
        );
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
