package FeatureTests;

import Features.Normalizers.Normalizer;
import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfWords;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StatusFeatureTest {
    @Test
    public void testGetClass(){
        Normalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        assertEquals(new NumberOfWords(), new NumberOfWords());
        assertNotEquals(new NumberOfWords(), new NumberOfWords(numberOfSentencesNormalizer));

    }
}
