package FeatureTests.NormalizerTests;

import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfWordsNormalizerTests {
    @Test
    public void testEquals(){
        assertEquals(new NumberOfWordsNormalizer(), new NumberOfWordsNormalizer());
        assertNotEquals(new NumberOfWordsNormalizer(), new NumberOfSentencesNormalizer());
    }
    @Test
    public void testReturnValue(){
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("I don't know why you say goodbye, I say hello hello hello.");

        assertEquals(12, new NumberOfWordsNormalizer().returnNormalizingValue(status), 0);
    }
}

