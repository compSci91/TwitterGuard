package Features.Normalizers;


import Features.NumberOfSentences;
import twitter4j.Status;

public class NumberOfSentencesNormalizer extends Normalizer {
    public double returnNormalizingValue(Status status) {
        return new NumberOfSentences().returnValue(status);
    }
}
