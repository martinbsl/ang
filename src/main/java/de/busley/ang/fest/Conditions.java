package de.busley.ang.fest;

import org.fest.assertions.core.Condition;

/**
 * @author Martin Busley
 */
public class Conditions {

    // static singleton
    private Conditions() {
    }

    public static <T> Condition<T> foul() {
        return new Condition<T>() {
            @Override
            public boolean matches(T value) {
                describedAs("foul");
                return false;
            }
        };
    }
}
