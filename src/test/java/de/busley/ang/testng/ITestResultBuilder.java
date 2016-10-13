package de.busley.ang.testng;


import org.testng.ITestResult;
import org.testng.internal.TestResult;

/**
 * @author Martin Busley
 */
public class ITestResultBuilder {

    private int status;

    private ITestResultBuilder() {
    }

    public static ITestResultBuilder iTestResult() {
        return new ITestResultBuilder();
    }

    public ITestResultBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ITestResult build() {
        ITestResult testResult = new TestResult();
        testResult.setStatus(status);

        return testResult;
    }
}
