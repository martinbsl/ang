package de.busley.ang.testng;

import org.fest.assertions.core.Condition;
import org.testng.TestNG;

import static org.fest.assertions.api.Assertions.not;

/**
 * @author Martin Busley
 */
public class TestNGConditions {

    private TestNGConditions() {}

    public static Condition<TestNG> success() {
        return not(failure());
    }

    public static Condition<TestNG> failure() {
        return new Condition<TestNG>() {
            @Override
            public boolean matches(TestNG testNG) {
                return testNG.hasFailure();
            }
        };
    }
}
