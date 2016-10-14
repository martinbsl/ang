package de.busley.ang;

import static de.busley.ang.TestStatus.SUCCESS;

/**
 * Marks failed tests as being successful. The test must be marked as {@code acceptance-test} and must not be defined
 * {@code done}.
 *
 * @author Martin Busley
 * @see de.busley.ang.AcceptanceTest
 * @see de.busley.ang.Done
 */
public final class AcceptanceTestMarker {

    /**
     * A test has failed and should be marked as {@code successful} if it is an acceptance test and has not yet been
     * marked as {@code done}.
     *
     * @param testExecutionResult The test execution result.
     */
    public void testFailed(TestExecutionResult testExecutionResult) {
        if (testExecutionResult.isAcceptanceTest() && !testExecutionResult.isDone()) {
            testExecutionResult.setStatus(SUCCESS);
        }
    }
}
