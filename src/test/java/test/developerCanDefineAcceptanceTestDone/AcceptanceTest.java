package test.developerCanDefineAcceptanceTestDone;

import de.busley.ang.testng.AcceptanceTestListener;
import de.busley.ang.testng.Done;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Martin Busley
 */
@Test
@Listeners(AcceptanceTestListener.class)
public class AcceptanceTest {

    @Test(groups = "failingTest_Done")
    @Done
    public void failingTest_Done() {
        assertThat(true).isFalse();
    }

    @Test(groups = "failingTest_NotDone")
    public void failingTest_NotDone() {
        assertThat(true).isFalse();
    }

    @Test(groups = "succeedingTest_Done")
    @Done
    public void succeedingTest_Done() {
        assertThat(true).isTrue();
    }

    @Test(groups = "succeedingTest_NotDone")
    public void succeedingTest_NotDone() {
        assertThat(true).isTrue();
    }
}
