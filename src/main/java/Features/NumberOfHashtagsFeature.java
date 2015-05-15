package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfHashtagsFeature implements StatusFeature {
    private Normalizer normalizer;

    public NumberOfHashtagsFeature(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfHashtagsFeature(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        return status.getHashtagEntities().length / normalizer.returnNormalizingValue(status);
    }
}
