package Features;

import twitter4j.Status;

public class NumberOfURLs implements StatusFeature{
    public double returnValue(Status status) {
        return status.getURLEntities().length;
    }
}
