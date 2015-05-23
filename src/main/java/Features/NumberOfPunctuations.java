package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfPunctuations implements StatusFeature {
    Normalizer normalizer;

    public NumberOfPunctuations(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfPunctuations(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        Matcher m = Pattern.compile("\\p{Punct}").matcher(status.getText());
        int numberOfPunctuation = 0;

        while (m.find()) {
            numberOfPunctuation++;
            m.group();
        }

        return numberOfPunctuation / normalizer.returnNormalizingValue(status);
    }
}
