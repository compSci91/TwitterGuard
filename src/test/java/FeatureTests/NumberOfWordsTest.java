package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfWords;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordsTest {
    Status status = mock(Status.class);

    @Before
    public void setUp(){
        when(status.getText()).thenReturn("I don't know why you say goodbye. I say hello hello hello.");
    }

    @Test
    public void testReturnValue(){
        assertEquals(12, new NumberOfWords().returnValue(status), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        assertEquals(12 / 2.0, new NumberOfWords(numberOfSentencesNormalizer).returnValue(status), 0);

    }
}
