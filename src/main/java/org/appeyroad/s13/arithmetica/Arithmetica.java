package org.appeyroad.s13.arithmetica;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class Arithmetica {

    private final List<String> ss;

    static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    public Arithmetica(final String... ss) {
        this.ss = new LinkedList<>(Arrays.asList(ss));
    }

    public void add(final String s) {
        ss.add(s);
    }

    public void clear() {
        ss.clear();
    }

    public Optional<Integer> evaluate() {
        final Tree t = new Tree();
        for (final String s : ss) {
            final boolean b = t.add(s);
            if (!b) {
                return Optional.empty();
            }
        }
        return Optional.of(t.evaluate());
    }

    public String formula() {
        return ss.stream().reduce((acc, s) -> {
            final boolean accLastDigit = !acc.isEmpty() &&
                    Character.isDigit(acc.charAt(acc.length() - 1));
            final boolean sFirstDigit = !s.isEmpty() &&
                    Character.isDigit(s.charAt(0));

            if (accLastDigit && sFirstDigit) {
                return acc + s;
            }

            return acc + " " + s;
        }).orElse("");
    }

    public static void main(String... args) {
        final String formula = "1 + 2 * 3 / 4 - 5 + 6 + 78";
        final String[] inputSequence = formula.replace(" ", "").split("");
        final Arithmetica arithmetica = new Arithmetica(inputSequence);
        final int value = arithmetica.evaluate().orElse(0);

        System.out.printf("%s = %d\n", formula, value);
        // Output: 1 + 2 * 3 / 4 - 5 + 6 + 78 = 81

        require(
                (1 + 2 * 3 / 4 - 5 + 6 + 78) == value,
                "Wrong value " + value
        );
        require(
                formula.equals(String.join(" ", arithmetica.formula())),
                "Wrong formula " + arithmetica.formula()
        );
    }
}
