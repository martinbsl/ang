package spec;

import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.developerCanMarkAcceptanceTest.AnAcceptanceTest;
import test.developerCanMarkAcceptanceTest.NotAnAcceptanceTest;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
public class DeveloperCanMarkAcceptanceTest {

    private TestNG testNG;

    @BeforeMethod
    public void setUp() throws Exception {
        testNG = new TestNG();
        testNG.setDefaultSuiteName("DeveloperCanMarkAcceptanceTest");
        testNG.setVerbose(0);
    }

    @Test
    public void failingTestIsSuccess_WhenMarkedAsAcceptanceTest() throws Exception {
        testNG.setTestClasses(new Class[]{AnAcceptanceTest.class});

        testNG.run();

        assertThat(testNG.hasFailure()).describedAs("testNG has failure").isFalse();
    }

    @Test
    public void failingTestIsFailure_WhenNotMarkedAsAcceptanceTest() throws Exception {
        testNG.setTestClasses(new Class[]{NotAnAcceptanceTest.class});

        testNG.run();

        assertThat(testNG.hasFailure()).describedAs("testNG has failure").isTrue();
    }
}
