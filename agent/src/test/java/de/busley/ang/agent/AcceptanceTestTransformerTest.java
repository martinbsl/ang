package de.busley.ang.agent;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.AnAcceptanceTest;

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
        AssertionError assertionError = catchAssertionError(new AnAcceptanceTest()::throwsAssertionError);

        assertThat(assertionError).isNull();
    }

    public void transformedMethodThrowsAssertionError_WhenMarkedAsDone() throws Throwable {
        AssertionError assertionError = catchAssertionError(new AnAcceptanceTest()::markedAsDone);

        assertThat(assertionError).isNull();
    }

    private static AssertionError catchAssertionError(Runnable runnable) throws Exception {
        AssertionError assertionError;
        try {
            runnable.run();
            assertionError = null;
        } catch (AssertionError e) {
            assertionError = e;
        }
        return assertionError;
    }
}
