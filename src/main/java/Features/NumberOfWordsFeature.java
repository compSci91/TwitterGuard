package Features;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfWordsFeature implements StatusFeature {

    public double returnValue(Status status) {
        return getWords(status).size();
    }

    private List<String> getWords(Status status){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\S&&[^,]]+").matcher(status.getText());

        while (m.find()) {
            words.add(m.group());
        }

        return words;
    }

}
