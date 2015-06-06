package Comparators;

import Corpus.*;
import Features.StatusFeature;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ZScoreDistanceTest {
    @Test
    public void getZScoreTest(){
        StatusFeature statusFeature = mock(StatusFeature.class);
        Corpus corpus = mock(Corpus.class);
        when(corpus.getMean(statusFeature)).thenReturn(5.0);
        when(corpus.getStandardDeviation(statusFeature)).thenReturn(2.0);

        Document queryDocument = mock(Document.class);
        when(queryDocument.getValueForFeature(statusFeature)).thenReturn(25.0);

        ZScoreDistance zScoreDistance = new ZScoreDistance(corpus);

        double actualZScoreDistance = zScoreDistance.getZScore(statusFeature, queryDocument);
        double expectedZScoreDistance = 10.0;

        assertEquals(expectedZScoreDistance, actualZScoreDistance, 0);
    }

    @Test
    public void getZScoreTestAgain(){
        StatusFeature statusFeature = mock(StatusFeature.class);
        Corpus corpus = mock(Corpus.class);
        when(corpus.getMean(statusFeature)).thenReturn(38.0);
        when(corpus.getStandardDeviation(statusFeature)).thenReturn(3.0);

        Document queryDocument = mock(Document.class);
        when(queryDocument.getValueForFeature(statusFeature)).thenReturn(89.0);

        ZScoreDistance zScoreDistance = new ZScoreDistance(corpus);

        double actualZScoreDistance = zScoreDistance.getZScore(statusFeature, queryDocument);
        double expectedZScoreDistance = 17.0;

        assertEquals(expectedZScoreDistance, actualZScoreDistance, 0);
    }
}