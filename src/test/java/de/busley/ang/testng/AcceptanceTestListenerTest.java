package de.busley.ang.testng;


import org.testng.ITestResult;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.ITestResult.FAILURE;
import static org.testng.ITestResult.SUCCESS;

/**
 * @author Martin Busley
 */
public class AcceptanceTestListenerTest {

    @Test
    public void setsTestResultStatusToSUCCESS_WhenTestFails() throws Exception {
        AcceptanceTestListener acceptanceTestListener = new AcceptanceTestListener();
        ITestResult testResult = new FakeTestResult();
        testResult.setStatus(FAILURE);

        acceptanceTestListener.onTestFailure(testResult);

        assertThat(testResult.getStatus()).describedAs("status").isEqualTo(SUCCESS);
    }
}