package de.busley.ang.testng;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.testng.TestNG;

/**
 * @author Martin Busley
 */
public class TestNGAssert extends AbstractAssert<TestNGAssert, TestNG> {

    private TestNGAssert(TestNG actual) {
        super(actual, TestNGAssert.class);
    }

    public static TestNGAssert assertThat(TestNG actual) {
        Assertions.assertThat(actual).isNotNull();
        return new TestNGAssert(actual);
    }

    public TestNGAssert hasFailure() {
        Assertions.assertThat(actual.hasFailure())
                .overridingErrorMessage("Expecting TestNG instance '%s' to have failed tests."
                        , actual.getDefaultSuiteName())
                .isTrue();
        return this;
    }

    public TestNGAssert isSuccess() {
        Assertions.assertThat(actual.hasFailure())
                .overridingErrorMessage("Expecting TestNG instance '%s' not to have failed tests."
                        , actual.getDefaultSuiteName())
                .isFalse();
        return this;
    }
}
