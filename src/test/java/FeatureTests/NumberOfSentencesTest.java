package FeatureTests;

import Features.NumberOfSentences;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfSentencesTest {
    Status status = mock(Status.class);
    NumberOfSentences numberOfSentences = new NumberOfSentences();

    @Test
    public void testReturnValue(){
        when(status.getText()).thenReturn("There are 2.0 sentences here. Not 3.");
        assertEquals(2.0, numberOfSentences.returnValue(status), 0);
    }

    @Test
    public void testGetNumberOfSentencesWithQuoationMarks(){
        when(status.getText()).thenReturn("\"There are 2.0 sentences here.\" Not 3.");
        assertEquals(2, numberOfSentences.returnValue(status), 0);
    }

    @Test
    public void testGetNumberOfSentencesWithApostrophe(){
       when(status.getText()).thenReturn("There are two sentences here. Don't deny it.");
        assertEquals(2, numberOfSentences.returnValue(status), 0);
    }
}
