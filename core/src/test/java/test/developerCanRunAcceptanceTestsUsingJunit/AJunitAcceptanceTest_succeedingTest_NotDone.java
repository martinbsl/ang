package test.developerCanRunAcceptanceTestsUsingJunit;

import de.busley.ang.junit.AcceptanceTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@RunWith(AcceptanceTestRunner.class)
public class AJunitAcceptanceTest_succeedingTest_NotDone {

    @Test
    public void succeedingTest_NotDone() {
        assertThat(true).isTrue();
    }
}
