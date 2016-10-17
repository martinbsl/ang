package de.busley.ang.testng;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.Done;
import de.busley.ang.TestExecutionResult;
import de.busley.ang.TestStatus;
import org.testng.ITestResult;

/**
 * @author Martin Busley
 */
final class TestNGTestExecutionResult implements TestExecutionResult {

    private final ITestResult testResult;

    TestNGTestExecutionResult(ITestResult testResult) {
        this.testResult = testResult;
    }

    @Override
    public void setStatus(TestStatus status) {
        switch (status) {
            case SUCCESS:
                testResult.setStatus(ITestResult.SUCCESS);
                break;
            case FAILURE:
                testResult.setStatus(ITestResult.FAILURE);
                break;
            case SKIPPED:
                testResult.setStatus(ITestResult.SKIP);
                break;
            default:
                throw new IllegalArgumentException("unsupported test-result status for TestNG: " + status);
        }
    }

    @Override
    public boolean isAcceptanceTest() {
        return testResult.getTestClass().getRealClass().getAnnotation(AcceptanceTest.class) != null;
    }

    @Override
    public boolean isDone() {
        return testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Done.class) != null;
    }
}
