package de.busley.ang.testng;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.AnnotationResolver;
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

    private AnnotationResolver<ITestResult> annotationResolver;

    public AcceptanceTestListener() {
        setAnnotationResolver(new ITestResultAnnotationResolver());
    }

    public void setAnnotationResolver(AnnotationResolver<ITestResult> annotationResolver) {
        this.annotationResolver = annotationResolver;
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        if (annotationResolver.hasAnnotation(testResult, AcceptanceTest.class)
                && !annotationResolver.hasAnnotation(testResult, Done.class)) {
            testResult.setStatus(SUCCESS);
        }
    }
}
