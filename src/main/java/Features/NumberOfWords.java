package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import Helpers.WordsHelper;
import twitter4j.Status;

public class NumberOfWords extends StatusFeature {
    WordsHelper wordsHelper = new WordsHelper();
    public NumberOfWords(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfWords(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        return wordsHelper.getWords(status).size() / normalizer.returnNormalizingValue(status);
    }
}
