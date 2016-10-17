package de.busley.ang.junit;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.junit.runner.Result;

/**
 * @author Martin Busley
 */
public class ResultAssert extends AbstractAssert<ResultAssert, Result> {
    private ResultAssert(Result actual) {
        super(actual, ResultAssert.class);
    }

    public static ResultAssert assertThat(Result actual) {
        Assertions.assertThat(actual).isNotNull();
        return new ResultAssert(actual);
    }

    public ResultAssert hasFailure() {
        Assertions.assertThat(actual.getFailureCount())
                .overridingErrorMessage("Expecting junit-test-run to have failed tests.")
                .isNotZero();
        return this;
    }

    public ResultAssert isSuccess() {
        Assertions.assertThat(actual.getFailureCount())
                .overridingErrorMessage("Expecting junit-test-run not to have failed tests.")
                .isZero();
        return this;
    }
}
