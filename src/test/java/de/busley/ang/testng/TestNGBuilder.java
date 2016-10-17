package de.busley.ang.testng;

import org.testng.TestNG;

/**
 * @author Martin Busley
 */
public class TestNGBuilder {

    private final String suiteName;
    private int verbose = 0;
    private Class[] testClasses;
    private String groups;

    private TestNGBuilder(String suiteName) {
        this.suiteName = suiteName;
    }

    public static TestNGBuilder testNG(String suiteName) {
        return new TestNGBuilder(suiteName);
    }

    public TestNGBuilder verbose(int verbose) {
        this.verbose = verbose;
        return this;
    }

    public TestNGBuilder testClasses(Class... testClasses) {
        this.testClasses = testClasses;
        return this;
    }

    public TestNGBuilder groups(String groups) {
        this.groups = groups;
        return this;
    }

    public TestNG build() {
        TestNG testNG = new TestNG();
        testNG.setDefaultSuiteName(suiteName);
        testNG.setVerbose(verbose);
        if (testClasses != null) {
            testNG.setTestClasses(testClasses);
        }
        testNG.setGroups(groups);

        return testNG;
    }
}
