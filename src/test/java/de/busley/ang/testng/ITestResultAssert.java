package de.busley.ang.testng;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.api.Assertions;
import org.testng.ITestResult;

/**
 * @author Martin Busley
 */
public class ITestResultAssert extends AbstractAssert<ITestResultAssert, ITestResult> {

    private ITestResultAssert(ITestResult actual) {
        super(actual, ITestResultAssert.class);
    }

    public static ITestResultAssert assertThat(ITestResult actual) {
        return new ITestResultAssert(actual);
    }

    public ITestResultAssert hasStatus(int status) {
        isNotNull();
        int actualStatus = actual.getStatus();
        Assertions.assertThat(actualStatus)
                .overridingErrorMessage(descriptionPrefix() + "expecting status to be %s but was %s."
                        , statusText(status), statusText(actualStatus))
                .isEqualTo(status);
        return this;
    }

    private String descriptionPrefix() {
        final String descriptionText = descriptionText();
        return descriptionText != null ? "[" + descriptionText + "] " : "";
    }

    private static String statusText(int status) {
        switch (status) {
            case ITestResult.FAILURE:
                return "FAILURE";
            case ITestResult.SKIP:
                return "SKIP";
            case ITestResult.STARTED:
                return "STARTED";
            case ITestResult.SUCCESS:
                return "SUCCESS";
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                return "SUCCESS_PERCENTAGE_FAILURE";
            default:
                throw new AssertionError("invalid ITestResult.status " + status);
        }
    }
}
