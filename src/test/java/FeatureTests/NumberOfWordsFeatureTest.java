package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfWordsFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordsFeatureTest {
    Status status = mock(Status.class);

    @Before
    public void setUp(){
        when(status.getText()).thenReturn("I don't know why you say goodbye. I say hello hello hello.");
    }

    @Test
    public void testReturnValue(){
        assertEquals(12, new NumberOfWordsFeature().returnValue(status), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        assertEquals(12 / 2.0, new NumberOfWordsFeature(numberOfSentencesNormalizer).returnValue(status), 0);

    }
}
