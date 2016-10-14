package de.busley.ang;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static de.busley.ang.TestStatus.SUCCESS;
import static org.mockito.Mockito.*;

@Test
public class AcceptanceTestMarkerTest {

    private AcceptanceTestMarker testMarker;
    private TestExecutionResult testExecutionResult;

    @BeforeMethod
    public void setUp() throws Exception {
        testMarker = new AcceptanceTestMarker();
        testExecutionResult = mock(TestExecutionResult.class);
    }

    public void setsTestResultToSUCCESS_WhenTestFailedAndAcceptanceTestNotDone() throws Exception {
        when(testExecutionResult.isAcceptanceTest()).thenReturn(true);
        when(testExecutionResult.isDone()).thenReturn(false);

        testMarker.testFailed(testExecutionResult);

        verify(testExecutionResult).setStatus(SUCCESS);
    }

    public void doesNotChangeTestStatus_WhenTestFailedButIsNotAnAcceptanceTest() throws Exception {
        when(testExecutionResult.isAcceptanceTest()).thenReturn(false);

        testMarker.testFailed(testExecutionResult);

        verify(testExecutionResult, times(0)).setStatus(any(TestStatus.class));
    }

    public void doesNotChangeTestStatus_WhenTestFailedButMarkedAsDone() throws Exception {
        when(testExecutionResult.isAcceptanceTest()).thenReturn(true);
        when(testExecutionResult.isDone()).thenReturn(true);

        testMarker.testFailed(testExecutionResult);

        verify(testExecutionResult, times(0)).setStatus(any(TestStatus.class));
    }
}