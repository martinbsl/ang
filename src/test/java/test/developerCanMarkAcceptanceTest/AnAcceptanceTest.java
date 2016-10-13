package test.developerCanMarkAcceptanceTest;

import de.busley.ang.testng.AcceptanceTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static de.busley.ang.fest.Conditions.foul;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@Test
@Listeners(AcceptanceTestListener.class)
public class AnAcceptanceTest {

    @Test
    public void aFailingTest() throws Exception {
        assertThat("fair").is(foul());
    }

    @Test
    public void aSuccessFulTest() throws Exception {
        assertThat(true).isTrue();
    }
}
