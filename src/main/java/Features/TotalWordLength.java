package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import Helpers.WordsHelper;
import twitter4j.Status;

import java.util.List;

public class TotalWordLength extends StatusFeature {
    WordsHelper wordsHelper = new WordsHelper();
    public TotalWordLength(){
        this.normalizer = new NoNormalization();
    }

    public TotalWordLength(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> words = wordsHelper.getWords(status);
        int totalWordLength = 0;

        for(String word : words){
            totalWordLength += word.length();
        }

        return totalWordLength / normalizer.returnNormalizingValue(status);
    }
}
