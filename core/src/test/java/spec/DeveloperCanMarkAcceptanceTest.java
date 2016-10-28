package spec;

import de.busley.ang.testng.AcceptanceTestListener;
import de.busley.ang.testng.TestNGBuilder;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.developerCanMarkAcceptanceTest.AnAcceptanceTest;
import test.developerCanMarkAcceptanceTest.NotAnAcceptanceTest;

import static de.busley.ang.testng.TestNGAssert.assertThat;
import static de.busley.ang.testng.TestNGBuilder.testNG;

/**
 * @author Martin Busley
 */
@Test
public class DeveloperCanMarkAcceptanceTest {

    private TestNGBuilder testNGBuilder;

    @BeforeMethod
    public void setUp() throws Exception {
        testNGBuilder = testNG("DeveloperCanMarkAcceptanceTest")
                .listeners(AcceptanceTestListener.class);
    }

    public void failingTestIsSuccess_WhenMarkedAsAcceptanceTest() throws Exception {
        TestNG testNG = testNGBuilder
                .testClasses(AnAcceptanceTest.class).build();

        testNG.run();

        assertThat(testNG).isSuccess();
    }

    public void failingTestIsFailure_WhenNotMarkedAsAcceptanceTest() throws Exception {
        TestNG testNG = testNGBuilder
                .testClasses(NotAnAcceptanceTest.class).build();

        testNG.run();

        assertThat(testNG).hasFailure();
    }
}
