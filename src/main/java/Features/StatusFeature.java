package Features;

import Features.Normalizers.Normalizer;
import twitter4j.Status;

public abstract class StatusFeature {
    protected Normalizer normalizer;
    public abstract double returnValue(Status status);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberOfWords that = (NumberOfWords) o;

        return !(normalizer != null ? !normalizer.equals(that.normalizer) : that.normalizer != null);
    }

    @Override
    public int hashCode() {
        return normalizer != null ? normalizer.hashCode() : 0;
    }
}
