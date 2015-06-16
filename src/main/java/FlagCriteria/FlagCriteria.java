package FlagCriteria;

import twitter4j.Status;

public interface FlagCriteria {
    public boolean isFlagged(Status status);
}
