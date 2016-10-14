package de.busley.ang.testng;

import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static de.busley.ang.testng.ITestResultAssert.assertThat;
import static de.busley.ang.testng.ITestResultBuilder.iTestResult;
import static org.testng.ITestResult.FAILURE;
import static org.testng.ITestResult.SUCCESS;

/**
 * @author Martin Busley
 */
@Test(enabled = false)
public class AcceptanceTestListenerTest {

    private AcceptanceTestListener acceptanceTestListener;

    @BeforeMethod
    public void setUp() throws Exception {
        acceptanceTestListener = new AcceptanceTestListener();
    }

    public void setsTestResultStatusToSUCCESS_WhenTestFails() throws Exception {
        ITestResult testResult = iTestResult().status(FAILURE).build();

        acceptanceTestListener.onTestFailure(testResult);

        assertThat(testResult).hasStatus(SUCCESS);
    }
}
