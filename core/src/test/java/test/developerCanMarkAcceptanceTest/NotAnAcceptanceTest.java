package test.developerCanMarkAcceptanceTest;

import org.testng.annotations.Test;

import static de.busley.ang.fest.Conditions.foul;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@Test
public class NotAnAcceptanceTest {

    public void aFailingTest() throws Exception {
        assertThat("fair").is(foul());
    }

    public void aSuccessFulTest() throws Exception {
        assertThat(true).isTrue();
    }
}
