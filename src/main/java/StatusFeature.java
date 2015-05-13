import twitter4j.Status;

public abstract class StatusFeature {
    Normalizer normalizer;

    public StatusFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    abstract double returnValue(Status status);
}
