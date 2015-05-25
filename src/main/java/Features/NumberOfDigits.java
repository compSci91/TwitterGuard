package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfDigits extends StatusFeature{
    public NumberOfDigits(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfDigits(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        String text = status.getText();
        int numberOfDigits = 0;

        for(int i = 0; i < text.length(); i++){
            if(Character.isDigit(text.charAt(i))){
                numberOfDigits++;
            }
        }

        return numberOfDigits / normalizer.returnNormalizingValue(status);
    }
}
