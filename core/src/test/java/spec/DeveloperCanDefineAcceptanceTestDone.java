package spec;

import de.busley.ang.testng.AcceptanceTestListener;
import de.busley.ang.testng.TestNGBuilder;
import org.fest.assertions.core.Condition;
import org.testng.TestNG;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.developerCanDefineAcceptanceTestDone.AnAcceptanceTest;

import static de.busley.ang.testng.TestNGAssert.assertThat;
import static de.busley.ang.testng.TestNGBuilder.testNG;
import static de.busley.ang.testng.TestNGConditions.failure;
import static de.busley.ang.testng.TestNGConditions.success;

/**
 * @author Martin Busley
 */
public class DeveloperCanDefineAcceptanceTestDone {

    private TestNGBuilder testNGBuilder;

    @BeforeMethod
    public void setUp() throws Exception {
        testNGBuilder = testNG("DeveloperCanDefineAcceptanceTestDone")
                .listeners(AcceptanceTestListener.class);
    }

    @Test(dataProvider = "testMethod_expectedOutcome")
    public void failingAcceptanceTestIsFAILURE_WhenMarkedAsDone(String testMethod, Condition<TestNG> expectedOutcome) throws Exception {
        TestNG testNG = testNGBuilder
                .testClasses(AnAcceptanceTest.class)
                .groups(testMethod)
                .build();

        testNG.run();

        assertThat(testNG).has(expectedOutcome);
    }

    @DataProvider
    public Object[][] testMethod_expectedOutcome() {
        return new Object[][]{
                {"failingTest_Done", failure()},
                {"failingTest_NotDone", success()},
                {"succeedingTest_Done", success()},
                {"succeedingTest_NotDone", success()}
        };
    }
}
