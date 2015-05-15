package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfDictionaryWordsFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfDictionaryWordsFeatureTest {
    Status twoWordStatus = mock(Status.class);

    @Before
    public void setUp(){
        when(twoWordStatus.getText()).thenReturn("two words lsjkdf ljskfd");
    }

    @Test
    public void testReturnValue() {
        assertEquals(2, new NumberOfDictionaryWordsFeature().returnValue(twoWordStatus), 0);
    }

    @Test

    public void testNormalizedValue(){
        Normalizer normalizer = new NumberOfWordsNormalizer();
        assertEquals(2/4.0, new NumberOfDictionaryWordsFeature(normalizer).returnValue(twoWordStatus), 0);

    }
}
