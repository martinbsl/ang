package de.busley.ang.testng;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.AnnotationResolver;
import de.busley.ang.Done;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static de.busley.ang.testng.ITestResultAssert.assertThat;
import static de.busley.ang.testng.ITestResultBuilder.iTestResult;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.ITestResult.FAILURE;
import static org.testng.ITestResult.SUCCESS;

/**
 * @author Martin Busley
 */
@Test
public class AcceptanceTestListenerTest {

    @SuppressWarnings("unchecked")
    private AnnotationResolver<ITestResult> annotationResolver = mock(AnnotationResolver.class);
    private AcceptanceTestListener acceptanceTestListener;

    @BeforeMethod
    public void setUp() throws Exception {
        acceptanceTestListener = new AcceptanceTestListener();
        acceptanceTestListener.setAnnotationResolver(annotationResolver);
    }

    public void setsTestResultStatusToSUCCESS_WhenTestFails() throws Exception {
        ITestResult testResult = iTestResult().status(FAILURE).build();
        when(annotationResolver.hasAnnotation(testResult, AcceptanceTest.class)).thenReturn(true);
        when(annotationResolver.hasAnnotation(testResult, Done.class)).thenReturn(false);

        acceptanceTestListener.onTestFailure(testResult);

        assertThat(testResult).hasStatus(SUCCESS);
    }
}
