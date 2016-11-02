package de.busley.ang.agent;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.instrument.Instrumentation;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

/**
 * @author Martin Busley
 */
@Test
@Listeners(MockitoTestNGListener.class)
public class AgentTest {

    private static final String EMPTY_ARGS = "";

    @Mock
    private Instrumentation instrumentation;

    public void agentMainAddsAcceptanceTestTransformer() throws Exception {
        Agent.agentmain(EMPTY_ARGS, instrumentation);

        verify(instrumentation).addTransformer(isA(AcceptanceTestTransformer.class));
    }

    public void preMainAddsAcceptanceTestTransformer() throws Exception {
        Agent.premain(EMPTY_ARGS, instrumentation);

        verify(instrumentation).addTransformer(isA(AcceptanceTestTransformer.class));
    }
}
