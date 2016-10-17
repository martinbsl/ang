package de.busley.ang;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a test-method "done". In that case the acceptance-test traverses into {@code regression} state
 * and must not fail any longer.
 *
 * @author Martin Busley
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Done {
}
