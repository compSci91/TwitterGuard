import twitter4j.Status;

public abstract class Normalizer {
    Status status;
    public Normalizer(Status status){
        this.status = status;
    };
}
