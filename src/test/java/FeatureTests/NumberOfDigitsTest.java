package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfDigits;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class NumberOfDigitsTest {
    Status status = mock(Status.class);

    @Before
    public void setUp(){
        when(status.getText()).thenReturn("There are 2.0 digits here.");
    }

    @Test
    public void testReturnValue(){
        assertEquals(2, new NumberOfDigits().returnValue(status), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();
        assertEquals(2 / 5.0, new NumberOfDigits(numberOfWordsNormalizer).returnValue(status), 0);
    }
}
