package test;

import de.busley.ang.AcceptanceTest;
import de.busley.ang.Done;

/**
 * @author Martin Busley
 */
@AcceptanceTest
public class AnAcceptanceTest {

    public void throwsAssertionError() {
        throw new AssertionError();
    }

    @Done
    public void markedAsDone() {
        throw new AssertionError();
    }
}
