package FeatureTests;

import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfBeginningOfSentenceWordsCapitalizedFeature;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfBeginningOfSentencesCapitalizedFeatureTest {
    Status status = mock(Status.class);

    @Before
    public void setUp(){
        when(status.getText()).thenReturn("nope. Yes. nope. nope. Yes.");
    }

    @Test
    public void testReturnValue(){
        assertEquals(2.0, new NumberOfBeginningOfSentenceWordsCapitalizedFeature().returnValue(status), 0);
    }


    @Test
    public void testNormalizedReturnValue(){
        NumberOfSentencesNormalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        NumberOfBeginningOfSentenceWordsCapitalizedFeature normalizedFeature = new NumberOfBeginningOfSentenceWordsCapitalizedFeature(numberOfSentencesNormalizer);

        assertEquals(2/5.0, normalizedFeature.returnValue(status), 0);
    }
}
