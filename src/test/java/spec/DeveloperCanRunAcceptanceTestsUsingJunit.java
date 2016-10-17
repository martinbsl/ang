package spec;

import org.fest.assertions.core.Condition;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static de.busley.ang.junit.JUnitConditions.failure;
import static de.busley.ang.junit.JUnitConditions.success;
import static de.busley.ang.junit.ResultAssert.assertThat;

/**
 * @author Martin Busley
 */
@Test
public class DeveloperCanRunAcceptanceTestsUsingJunit {

    @Test(dataProvider = "testMethod_expectedOutcome")
    public void acceptanceTestsCanBeExecutedUsingJunit(String testMethod, Condition<Result> expectedOutcome) throws Exception {
        JUnitCore junit = new JUnitCore();

        Result result = junit.run(Class.forName(
                        "test.developerCanRunAcceptanceTestsUsingJunit.AJunitAcceptanceTest_" + testMethod)
        );

        assertThat(result).has(expectedOutcome);
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
