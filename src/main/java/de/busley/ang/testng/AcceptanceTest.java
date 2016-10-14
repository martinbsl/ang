package de.busley.ang.testng;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a test-class an "acceptance-test". Acceptance tests are allowed or even expected to fail until
 * the required feature has been implemented.
 * <p>
 * This annotation signals, that a failure of a test-method should not break the current build unless it is marked as "done".
 *
 * @author Martin Busley
 * @see de.busley.ang.testng.Done
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface AcceptanceTest {
}
