package FeatureTests;

import Features.NumberOfWordsFeature;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordsFeatureTest {
    @Test
    public void testReturnValue(){
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("I don't know why you say goodbye, I say hello hello hello.");

        assertEquals(12, new NumberOfWordsFeature().returnValue(status), 0);
    }
}
