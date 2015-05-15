package FeatureTests;

import Features.AverageNumberOfWordsPerSentenceFeature;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AverageNumberOfWordsPerSentencesFeatureTest {
    @Test
    public void testReturnValue(){
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("A simple sentence. A more complex sentence. A still more complex sentence.");

        assertEquals(4.0, new AverageNumberOfWordsPerSentenceFeature().returnValue(status), 0);

    }
}
