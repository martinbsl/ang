package de.busley.ang.agent;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.security.ProtectionDomain;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@Test
public class AcceptanceTestTransformerTest {

    private static final ClassLoader USE_BOOTSTRAP_LOADER = null;
    private static final Class<?> NOT_BEING_REDEFINED = null;
    private static final ProtectionDomain PROTECTION_DOMAIN = AcceptanceTestTransformerTest.class.getProtectionDomain();
    private static final byte[] CLASSFILE_BUFFER = new byte[0];
    private AcceptanceTestTransformer acceptanceTestTransformer;

    @BeforeMethod
    public void setUp() throws Exception {
        acceptanceTestTransformer = new AcceptanceTestTransformer();
    }

    public void returnsNull_WhenClassIsNotAnAcceptanceTest() throws Exception {
        byte[] transformedClass = acceptanceTestTransformer.transform(
                USE_BOOTSTRAP_LOADER, "test.NotAnAcceptanceTest", NOT_BEING_REDEFINED, PROTECTION_DOMAIN, CLASSFILE_BUFFER
        );

        assertThat(transformedClass).isNull();
    }

    public void transformedMethodDoesNotThrowAssertionError_WhenClassIsAnAcceptanceTest() throws Exception {
        byte[] transformedClass = acceptanceTestTransformer.transform(
                USE_BOOTSTRAP_LOADER, "test.AnAcceptanceTest", NOT_BEING_REDEFINED, PROTECTION_DOMAIN, CLASSFILE_BUFFER
        );
        Class anAcceptanceTestClass = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if (name.equals("test.AnAcceptanceTest")) {
                    return defineClass(name, transformedClass, 0, transformedClass.length);
                } else {
                    return super.loadClass(name);
                }
            }
        }.loadClass("test.AnAcceptanceTest");

        Object anAcceptanceTest = anAcceptanceTestClass.newInstance();

        anAcceptanceTestClass.getDeclaredMethod("throwsAssertionError").invoke(anAcceptanceTest);
    }

    public void transformedMethodThrowsAssertionError_WhenMarkedAsDone() throws Throwable {
        byte[] transformedClass = acceptanceTestTransformer.transform(
                USE_BOOTSTRAP_LOADER, "test.AnAcceptanceTest", NOT_BEING_REDEFINED, PROTECTION_DOMAIN, CLASSFILE_BUFFER
        );
        Class anAcceptanceTestClass = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                if (name.equals("test.AnAcceptanceTest")) {
                    return defineClass(name, transformedClass, 0, transformedClass.length);
                } else {
                    return super.loadClass(name);
                }
            }
        }.loadClass("test.AnAcceptanceTest");

        Object anAcceptanceTest = anAcceptanceTestClass.newInstance();

        AssertionError assertionError = null;
        try {
            anAcceptanceTestClass.getDeclaredMethod("markedAsDone").invoke(anAcceptanceTest);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof AssertionError) {
                assertionError = (AssertionError) e.getTargetException();
            } else {
                throw e.getTargetException();
            }
        }
        assertThat(assertionError).isNotNull();
    }
}
