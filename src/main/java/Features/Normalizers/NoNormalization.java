package Features.Normalizers;

import twitter4j.Status;

public class NoNormalization implements Normalizer{
    public double returnNormalizingValue(Status status) {
        return 1;
    }
}
