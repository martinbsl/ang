package de.busley.ang;

import java.lang.annotation.Annotation;

/**
 * An {@code AnnotationResolver} can be used to access annotations of test-methods or -classes.
 *
 * @param <T> The type of object wrapping the java-reflection instance type holding the annotations.
 * @author Martin Busley
 */
public interface AnnotationResolver<T> {

    /**
     * Checks for the existence of an annotation.
     *
     * @param testInstance    The instance wrapper to check for annotations.
     * @param annotationClass The type of annotation to check for.
     * @return {@code true} if an annotation of that type is defined
     */
    boolean hasAnnotation(T testInstance, Class<? extends Annotation> annotationClass);
}
