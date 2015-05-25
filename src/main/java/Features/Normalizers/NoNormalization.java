package Features.Normalizers;

import twitter4j.Status;

public class NoNormalization extends Normalizer{
    public double returnNormalizingValue(Status status) {
        return 1;
    }
}
