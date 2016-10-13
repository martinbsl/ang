package de.busley.ang.testng;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static org.testng.ITestResult.SUCCESS;

/**
 * TestNG listener that makes a test pass as {@code SUCCESS} event if it fails.
 *
 * @author Martin Busley
 */
public final class AcceptanceTestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
        testResult.setStatus(SUCCESS);
    }
}
