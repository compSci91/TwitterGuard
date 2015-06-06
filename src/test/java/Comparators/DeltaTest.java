package Comparators;


import Corpus.*;
import Features.Normalizers.NumberOfSentencesNormalizer;
import Features.NumberOfSentences;
import Features.NumberOfWords;
import Features.StatusFeature;
import org.junit.Test;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeltaTest {
    @Test
    public void testCompartor(){
        Status queryStatus = mock(Status.class);
        when(queryStatus.getText()).thenReturn("Josh Howell was here. So was I.");

        Document queryDocument = new Document(queryStatus, "Josh Howell", new ArrayList<StatusFeature>(
                Arrays.asList(new NumberOfWords(), new NumberOfSentences(), new NumberOfWords(new NumberOfSentencesNormalizer()))
        ));

        Status document1Status = mock(Status.class);
        when(queryStatus.getText()).thenReturn("Josh was here. So was I.");

        Document document1 = new Document(queryStatus, "Josh", new ArrayList<StatusFeature>(
                Arrays.asList(new NumberOfWords(), new NumberOfSentences(), new NumberOfWords(new NumberOfSentencesNormalizer()))
        ));

        Status document2Status = mock(Status.class);
        when(queryStatus.getText()).thenReturn("Josh was here.");

        Document document2 = new Document(queryStatus, "Someone", new ArrayList<StatusFeature>(
                Arrays.asList(new NumberOfWords(), new NumberOfSentences(), new NumberOfWords(new NumberOfSentencesNormalizer()))
        ));

        PriorityQueue<Document> deltaRule = new PriorityQueue<Document>(new Delta(new Corpus(
                new ArrayList<Document>(Arrays.asList(document2, document1, queryDocument))
        ), queryDocument));


    }
}