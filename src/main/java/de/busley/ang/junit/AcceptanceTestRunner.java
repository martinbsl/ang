package de.busley.ang.junit;

import de.busley.ang.AcceptanceTestMarker;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.*;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;


/**
 * JUnit-Runner that makes a test an acceptance-test by making failed tests pass unless they are marked as {@code done}.
 *
 * @author Martin Busley
 * @see de.busley.ang.Done
 */
public final class AcceptanceTestRunner extends Runner implements Filterable, Sortable {

    private final BlockJUnit4ClassRunner runner;
    private final Class<?> testClass;

    public AcceptanceTestRunner(Class<?> testClass) throws InitializationError {
        this.testClass = testClass;
        runner = new BlockJUnit4ClassRunner(testClass);
    }

    @Override
    public Description getDescription() {
        return runner.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        runner.run(new MarkedNotifier(notifier, testClass));
    }

    @Override
    public void filter(Filter filter) throws NoTestsRemainException {
        runner.filter(filter);
    }

    @Override
    public void sort(Sorter sorter) {
        runner.sort(sorter);
    }

    private static class MarkedNotifier extends RunNotifierAdapter {

        private final Class<?> testClass;
        private final AcceptanceTestMarker acceptanceTestMarker;

        MarkedNotifier(RunNotifier delegate, Class<?> testClass) {
            super(delegate);
            this.testClass = testClass;
            acceptanceTestMarker = new AcceptanceTestMarker();
        }

        @Override
        public void fireTestFailure(Failure failure) {
            JUnitAcceptanceTestExecutionResult testExecutionResult =
                    new JUnitAcceptanceTestExecutionResult(testClass, failure.getDescription().getMethodName());

            acceptanceTestMarker.testFailed(testExecutionResult);

            switch (testExecutionResult.getStatus()) {
                case FAILURE:
                    delegate.fireTestFailure(failure);
                    break;
                case SUCCESS:
                    delegate.fireTestFinished(failure.getDescription());
                    break;
                case SKIPPED:
                    delegate.fireTestIgnored(failure.getDescription());
                    break;
            }
        }
    }
}
