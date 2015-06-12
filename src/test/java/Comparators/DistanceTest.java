package Comparators;

import Corpus.Corpus;
import Corpus.Document;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DistanceTest {
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

        Comparator<Document> distanceComparator = new Distance(corpus, queryDocument);
        PriorityQueue<Document> distanceQueue = new PriorityQueue<Document>(new Distance(corpus, queryDocument));

        distanceQueue.add(queryDocument);
        distanceQueue.add(document1);

        assertEquals(distanceQueue.peek().AUTHOR, "Josh Howell");
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
        Comparator<Document> distanceComparator = new Distance(corpus, queryDocument);

        PriorityQueue<Document> distanceQueue = new PriorityQueue<Document>(new Distance(corpus, queryDocument));

        distanceQueue.add(document2);
        distanceQueue.add(document1);
        distanceQueue.add(queryDocument);

        assertEquals("Josh Howell", distanceQueue.peek().AUTHOR);

    }
}