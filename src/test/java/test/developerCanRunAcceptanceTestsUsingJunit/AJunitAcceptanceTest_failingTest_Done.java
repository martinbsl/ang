package test.developerCanRunAcceptanceTestsUsingJunit;

import de.busley.ang.Done;
import de.busley.ang.junit.AcceptanceTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@RunWith(AcceptanceTestRunner.class)
public class AJunitAcceptanceTest_failingTest_Done {

    @Test
    @Done
    public void failingTest_Done() {
        assertThat(true).isFalse();
    }
}
