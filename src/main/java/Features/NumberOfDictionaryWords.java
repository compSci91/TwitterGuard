package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import Helpers.WordsHelper;
import twitter4j.Status;

import java.util.List;

public class NumberOfDictionaryWords extends StatusFeature{
    WordsHelper wordsHelper = new WordsHelper();
    public NumberOfDictionaryWords(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfDictionaryWords(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        List<String> words = wordsHelper.getWords(status);
        int numberOfDictionaryWords = 0;

        for(String word : words){
            if(wordsHelper.isDictionaryWord(word)){
                numberOfDictionaryWords++;
            }
        }

        return numberOfDictionaryWords / normalizer.returnNormalizingValue(status);
    }
}
