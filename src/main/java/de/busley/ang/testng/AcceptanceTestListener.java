package de.busley.ang.testng;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.Done;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static org.testng.ITestResult.SUCCESS;

/**
 * TestNG listener that makes a test pass as {@code SUCCESS} even if it fails.
 *
 * @author Martin Busley
 */
public final class AcceptanceTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
        if (testResult.getTestClass().getRealClass().getAnnotation(AcceptanceTest.class) != null
                && testResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Done.class) == null) {
            testResult.setStatus(SUCCESS);
        }
    }
}
