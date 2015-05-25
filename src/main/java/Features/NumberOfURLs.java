package Features;

import twitter4j.Status;

public class NumberOfURLs extends StatusFeature{
    public double returnValue(Status status) {
        return status.getURLEntities().length;
    }
}
