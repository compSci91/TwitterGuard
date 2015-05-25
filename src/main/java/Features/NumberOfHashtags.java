package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfHashtags extends StatusFeature {

    public NumberOfHashtags(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfHashtags(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        return status.getHashtagEntities().length / normalizer.returnNormalizingValue(status);
    }
}
