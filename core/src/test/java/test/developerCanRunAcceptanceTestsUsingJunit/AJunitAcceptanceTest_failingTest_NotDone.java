package test.developerCanRunAcceptanceTestsUsingJunit;

import de.busley.ang.AcceptanceTest;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@AcceptanceTest
public class AJunitAcceptanceTest_failingTest_NotDone {

    @Test
    public void failingTest_NotDone() {
        assertThat(true).isFalse();
    }
}
