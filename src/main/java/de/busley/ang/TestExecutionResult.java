package de.busley.ang;

/**
 * Interface to a test-systems test-result instance. Can be used to query/modify the status and other information
 * about a test-execution.
 *
 * @author Martin Busley
 */
public interface TestExecutionResult {

    /**
     * Sets the execution result status.
     *
     * @param status The status of the test execution
     */
    void setStatus(TestStatus status);

    /**
     * Has an acceptance test been executed?
     *
     * @return {@code true} when the test is an acceptance test.
     * @see de.busley.ang.AcceptanceTest
     */
    boolean isAcceptanceTest();

    /**
     * In case an acceptance test has been executed this flag gives information about the state of development.
     *
     * @return {@code true} when an acceptance test has been marked as "done" by the developer
     * @see de.busley.ang.Done
     * @see de.busley.ang.AcceptanceTest
     */
    boolean isDone();
}
