package de.busley.ang.junit;

import org.fest.assertions.core.Condition;
import org.junit.runner.Result;

import static org.fest.assertions.api.Assertions.not;

/**
 * @author Martin Busley
 */
public class JUnitConditions {

    private JUnitConditions() {
    }

    public static Condition<Result> success() {
        return not(failure());
    }

    public static Condition<Result> failure() {
        return new Condition<Result>() {
            @Override
            public boolean matches(Result result) {
                return result.getFailureCount() > 0;
            }
        }.describedAs("failure");
    }
}
