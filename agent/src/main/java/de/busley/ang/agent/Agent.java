package de.busley.ang.agent;

import java.lang.instrument.Instrumentation;

/**
 * Java-Agent that manipulates test-methods to run successfully for acceptance-tests unless marked as {@code done}.
 *
 * @author Martin Busley
 */
@SuppressWarnings("UnusedParameters")
public final class Agent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        agentmain(agentArgs, instrumentation);
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        instrumentation.addTransformer(new AcceptanceTestTransformer());
    }
}
