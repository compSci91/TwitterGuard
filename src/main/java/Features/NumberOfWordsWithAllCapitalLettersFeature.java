package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import Helpers.WordsHelper;
import twitter4j.Status;

import java.util.List;


public class NumberOfWordsWithAllCapitalLettersFeature extends StatusFeature {
    WordsHelper wordsHelper = new WordsHelper();

    public NumberOfWordsWithAllCapitalLettersFeature(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfWordsWithAllCapitalLettersFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> words = wordsHelper.getWords(status);
        int numberOfWordsWithAllCapitalLetters = 0;

        for(String word : words){
            if(word.equals(word.toUpperCase())){
                numberOfWordsWithAllCapitalLetters++;
            }
        }

        return numberOfWordsWithAllCapitalLetters / normalizer.returnNormalizingValue(status);
    }
}
