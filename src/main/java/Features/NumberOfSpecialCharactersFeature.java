package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfSpecialCharactersFeature implements StatusFeature{

    Normalizer normalizer;

    public NumberOfSpecialCharactersFeature(Normalizer normalizer) {
        this.normalizer = normalizer;
    }

    public NumberOfSpecialCharactersFeature() {
        this.normalizer = new NoNormalization();
    }

    public double returnValue(Status status) {
        String text = status.getText();
        int numberOfSpecialCharacters = 0;

        for(int i = 0; i < text.length(); i++){
            if(isSpecialCharacter(text.charAt(i))){
                numberOfSpecialCharacters++;
            }
        }

        return numberOfSpecialCharacters;
    }

    private boolean isSpecialCharacter(char character) {
        String keyboardCharacters = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";

        return !Character.isLetterOrDigit(character) &&
                !Character.isWhitespace(character) &&
                keyboardCharacters.indexOf(character) == -1 ;
    }
}
