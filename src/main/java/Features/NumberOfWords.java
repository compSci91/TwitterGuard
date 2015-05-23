package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfWords implements StatusFeature {
    Normalizer normalizer;

    public NumberOfWords(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfWords(Normalizer normalizer){
        this.normalizer = normalizer;
    }


    public double returnValue(Status status) {

        return getWords(status).size() / normalizer.returnNormalizingValue(status);
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
