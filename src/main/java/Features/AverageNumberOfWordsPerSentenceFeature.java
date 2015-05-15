package Features;

import twitter4j.Status;

public class AverageNumberOfWordsPerSentenceFeature implements StatusFeature {
    public double returnValue(Status status) {
       return new NumberOfWordsFeature().returnValue(status ) / new NumberOfSentencesFeature().returnValue(status);
    }
}
