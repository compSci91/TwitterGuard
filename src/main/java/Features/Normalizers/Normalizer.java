package Features.Normalizers;

import twitter4j.Status;

public interface  Normalizer {
    double returnNormalizingValue(Status status);
}
