package Features;

import Features.Normalizers.Normalizer;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PercentageOfWordsWithAllCapitalLettersFeature implements StatusFeature {
    Normalizer normalizer;

    public PercentageOfWordsWithAllCapitalLettersFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> words = getWords(status);
        int numberOfWordsWithAllCapitalLetters = 0;

        for(String word : words){
            if(word.equals(word.toUpperCase())){
                numberOfWordsWithAllCapitalLetters++;
            }
        }

        return numberOfWordsWithAllCapitalLetters / normalizer.returnNormalizingValue(status);
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
