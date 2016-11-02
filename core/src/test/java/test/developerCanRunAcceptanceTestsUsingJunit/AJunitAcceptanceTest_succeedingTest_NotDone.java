package test.developerCanRunAcceptanceTestsUsingJunit;

import de.busley.ang.AcceptanceTest;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@AcceptanceTest
public class AJunitAcceptanceTest_succeedingTest_NotDone {

    @Test
    public void succeedingTest_NotDone() {
        assertThat(true).isTrue();
    }
}
