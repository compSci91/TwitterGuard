package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.PercentageOfWordsWithAllCapitalLettersFeature;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PercentageOfWordsWithAllCapitalLettersTest {
    @Test
    public void testReturnValue(){
        Status status = mock(Status.class);
        when(status.getText()).thenReturn("THERE are TWO all cap words");

        assertEquals(2/6.0, new PercentageOfWordsWithAllCapitalLettersFeature(new NumberOfWordsNormalizer()).returnValue(status), 0);
    }
}
