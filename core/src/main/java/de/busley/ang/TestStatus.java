package de.busley.ang;

/**
 * Provides values for the status of a test-execution.
 *
 * @author Martin Busley
 * @see de.busley.ang.TestExecutionResult
 */
public enum TestStatus {

    /**
     * The test has successfully been executed.
     */
    SUCCESS,

    /**
     * The test execution has failed.
     */
    FAILURE,

    /**
     * The test execution has been skipped.
     */
    SKIPPED,

    /**
     * The execution status is (currently) undefined, for instance when a test execution is currently in progress.
     */
    UNDEFINED
}
