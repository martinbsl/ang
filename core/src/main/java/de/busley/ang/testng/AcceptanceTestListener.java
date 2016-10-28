package de.busley.ang.testng;

import de.busley.ang.AcceptanceTestMarker;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * TestNG listener that makes a test pass as {@code SUCCESS} even if it fails.
 *
 * @author Martin Busley
 * @see de.busley.ang.AcceptanceTestMarker
 */
public final class AcceptanceTestListener extends TestListenerAdapter {

    private final AcceptanceTestMarker acceptanceTestMarker = new AcceptanceTestMarker();

    @Override
    public void onTestFailure(ITestResult testResult) {
//        acceptanceTestMarker.testFailed(new TestNGTestExecutionResult(testResult));
    }
}
