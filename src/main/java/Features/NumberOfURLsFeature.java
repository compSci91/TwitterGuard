package Features;

import twitter4j.Status;

public class NumberOfURLsFeature implements StatusFeature{
    public double returnValue(Status status) {
        return status.getURLEntities().length;
    }
}
