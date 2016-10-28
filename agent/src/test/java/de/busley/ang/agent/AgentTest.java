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

    @Mock
    private Instrumentation instrumentation;

    public void agentMainAddsAcceptanceTestTransformer() throws Exception {
        Agent.agentMain("", instrumentation);

        verify(instrumentation).addTransformer(isA(AcceptanceTestTransformer.class));
    }

    public void preMainAddsAcceptanceTestTransformer() throws Exception {
        Agent.preMain("", instrumentation);

        verify(instrumentation).addTransformer(isA(AcceptanceTestTransformer.class));
    }
}
