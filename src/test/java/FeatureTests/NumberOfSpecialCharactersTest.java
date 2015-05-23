package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfWordsNormalizer;
import Features.NumberOfSpecialCharacters;
import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberOfSpecialCharactersTest {
    Status twoSpecialCharacters = mock(Status.class);

    @Before
    public void setUp(){
        when(twoSpecialCharacters.getText()).thenReturn("↪ Ⓐ three random words");
    }

    @Test
    public void testReturnValue(){
        assertEquals(2, new NumberOfSpecialCharacters().returnValue(twoSpecialCharacters), 0);
    }

    @Test
    public void testReturnNormalizedValue(){
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();
        assertEquals(2, new NumberOfSpecialCharacters(numberOfWordsNormalizer).returnValue(twoSpecialCharacters), 0);

    }
}
