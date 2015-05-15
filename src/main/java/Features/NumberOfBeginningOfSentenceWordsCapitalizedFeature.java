package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

import java.util.Arrays;
import java.util.List;

public class NumberOfBeginningOfSentenceWordsCapitalizedFeature implements StatusFeature{
    Normalizer normalizer;

    public NumberOfBeginningOfSentenceWordsCapitalizedFeature(){
        this. normalizer = new NoNormalization();
    }
    public NumberOfBeginningOfSentenceWordsCapitalizedFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> sentences = getSentences(status);
        int numberOfBeginningOfSentenceWordsCapitalized = 0;

        for(String sentence : sentences){
            if(Character.isUpperCase(sentence.charAt(0))){
                numberOfBeginningOfSentenceWordsCapitalized++;
            }
        }
        return numberOfBeginningOfSentenceWordsCapitalized / normalizer.returnNormalizingValue(status);
    }

    public List<String> getSentences(Status status) {

        String[] sentences = status.getText().split("(?<=[.?!])[\\s\"]+(?=[a-zA-Z])");
        return Arrays.asList(sentences);
    }
}
