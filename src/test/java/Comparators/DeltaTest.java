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
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeltaTest {
    @Test
    public void testCompare(){
        ArrayList<StatusFeature> statusFeatures = new ArrayList<StatusFeature>(
                Arrays.asList(new NumberOfWords(), new NumberOfSentences(), new NumberOfWords(new NumberOfSentencesNormalizer()))
        );

        Status queryStatus = mock(Status.class);
        when(queryStatus.getText()).thenReturn("Josh Howell was here. So was I.");
        Document queryDocument = new Document(queryStatus, "Josh Howell", statusFeatures);

        Status document1Status = mock(Status.class);
        when(document1Status.getText()).thenReturn("Josh was here. So was I.");
        Document document1 = new Document(document1Status, "Josh", statusFeatures);

        Corpus corpus = new Corpus(Arrays.asList(queryDocument, document1));

        Comparator<Document> deltaComparator = new Delta(corpus, queryDocument);
        PriorityQueue<Document> deltaQueue = new PriorityQueue<Document>(new Delta(corpus, queryDocument));

        deltaQueue.add(queryDocument);
        deltaQueue.add(document1);

        assertEquals(deltaQueue.peek().AUTHOR, "Josh Howell");
    }

    @Test
    public void testComparator(){

        ArrayList<StatusFeature> statusFeatures = new ArrayList<StatusFeature>(
                Arrays.asList(new NumberOfWords(), new NumberOfSentences(), new NumberOfWords(new NumberOfSentencesNormalizer()))
        );

        Status queryStatus = mock(Status.class);
        when(queryStatus.getText()).thenReturn("Josh Howell was here. So was I.");
        Document queryDocument = new Document(queryStatus, "Josh Howell", statusFeatures);

        Status document1Status = mock(Status.class);
        when(document1Status.getText()).thenReturn("Josh was here. So was I.");
        Document document1 = new Document(document1Status, "Josh", statusFeatures);

        Status document2Status = mock(Status.class);
        when(document2Status.getText()).thenReturn("Josh was here.");
        Document document2 = new Document(document2Status, "Someone", statusFeatures);

        Corpus corpus = new Corpus(Arrays.asList(queryDocument, document1, document2));
        Comparator<Document> deltaComparator = new Delta(corpus, queryDocument);

        PriorityQueue<Document> deltaQueue = new PriorityQueue<Document>(new Delta(corpus, queryDocument));

        deltaQueue.add(document2);
        deltaQueue.add(document1);
        deltaQueue.add(queryDocument);

        assertEquals("Josh Howell", deltaQueue.peek().AUTHOR);

    }


}