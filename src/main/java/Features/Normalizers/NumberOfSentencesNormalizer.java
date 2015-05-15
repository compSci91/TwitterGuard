package Features.Normalizers;


import Features.NumberOfSentencesFeature;
import twitter4j.Status;

public class NumberOfSentencesNormalizer implements Normalizer {
    public double returnNormalizingValue(Status status) {
        return new NumberOfSentencesFeature().returnValue(status);
    }
}
