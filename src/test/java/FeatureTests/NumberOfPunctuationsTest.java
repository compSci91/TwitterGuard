package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfPunctuations;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfPunctuationsTest {
    Status status = mock(Status.class);

    @Before
    public void setUp(){
        when(status.getText()).thenReturn("There are two sentences here. Don't deny it.");
    }

    @Test
    public void testReturnValue(){
        assertEquals(3.0, new NumberOfPunctuations().returnValue(status), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer normalizer = new NumberOfSentencesNormalizer();
        assertEquals(3/2.0, new NumberOfPunctuations(normalizer).returnValue(status), 0);
    }
}
