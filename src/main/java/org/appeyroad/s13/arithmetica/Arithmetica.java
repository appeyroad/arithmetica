package org.appeyroad.s13.arithmetica;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class Arithmetica {

    private final List<String> ss;

    Arithmetica(final String... ss) {
        this.ss = new LinkedList<>(Arrays.asList(ss));
    }

    public void add(final String s) {
        ss.add(s);
    }

    public Integer evaluate() {
        final Tree t = new Tree();
        for (final String s : ss) {
            final boolean b = t.add(s);
            if (!b) {
                return null;
            }
        }
        return t.evaluate();
    }

    public static void main(String... args) {
        final Arithmetica a = new Arithmetica("1 + 2 * 3 / 4 - 5 + 6".split(" "));
        final Integer v = a.evaluate();
        System.out.println(v);
    }

}
