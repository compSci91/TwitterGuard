package Features.Normalizers;

import twitter4j.Status;

public abstract class  Normalizer {
    public abstract double returnNormalizingValue(Status status);

    @Override
    public boolean equals(Object o){
        return getClass() == o.getClass();
    }

}
