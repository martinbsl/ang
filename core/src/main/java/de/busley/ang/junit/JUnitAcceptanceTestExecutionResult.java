package de.busley.ang.junit;

import de.busley.ang.Done;
import de.busley.ang.TestExecutionResult;
import de.busley.ang.TestStatus;

import java.lang.reflect.Method;

/**
 * @author Martin Busley
 */
final class JUnitAcceptanceTestExecutionResult implements TestExecutionResult {

    private final Method testMethod;
    private TestStatus status;

    JUnitAcceptanceTestExecutionResult(Class<?> testClass, String methodName) {
        try {
            testMethod = testClass.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            throw new InternalError("Test method " + methodName + " of class " + testClass.getName() + " not found.", e);
        }
    }

    @Override
    public void setStatus(TestStatus status) {
        this.status = status;
    }

    TestStatus getStatus() {
        return status;
    }

    @Override
    public boolean isAcceptanceTest() {
        return true;
    }

    @Override
    public boolean isDone() {
        return testMethod.getAnnotation(Done.class) != null;
    }
}
