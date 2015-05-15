package Features;

import twitter4j.Status;

import java.util.Arrays;
import java.util.List;

public class NumberOfSentencesFeature implements StatusFeature {
    public double returnValue(Status status) {
        return getSentences(status).size();
    }

    private static List<String> getSentences(Status status) {
        String[] sentences = status.getText().split("(?<=[.?!])[\\s\"]+(?=[a-zA-Z])");
        return Arrays.asList(sentences);
    }
}
