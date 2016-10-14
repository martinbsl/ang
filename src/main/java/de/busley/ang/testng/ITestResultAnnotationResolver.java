package de.busley.ang.testng;

import de.busley.ang.AnnotationResolver;
import org.testng.ITestResult;

import java.lang.annotation.Annotation;

/**
 * @author Martin Busley
 */
final class ITestResultAnnotationResolver implements AnnotationResolver<ITestResult> {

    @Override
    public boolean hasAnnotation(ITestResult testInstance, Class<? extends Annotation> annotationClass) {
        return testInstance.getMethod().getConstructorOrMethod().getMethod().getAnnotation(annotationClass) != null
                || testInstance.getTestClass().getRealClass().getAnnotation(annotationClass) != null;
    }
}
