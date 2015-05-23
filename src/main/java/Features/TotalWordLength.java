package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joshuahowell on 5/19/15.
 */
public class TotalWordLength implements StatusFeature {
    Normalizer normalizer;

    public TotalWordLength(){
        this.normalizer = new NoNormalization();
    }

    public TotalWordLength(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> words = getWords(status);
        int totalWordLength = 0;

        for(String word : words){
            totalWordLength += word.length();
        }

        return totalWordLength / normalizer.returnNormalizingValue(status);
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
