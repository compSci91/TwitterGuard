package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import twitter4j.Status;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfWordExtensions implements StatusFeature {
    Normalizer normalizer;

    public NumberOfWordExtensions(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfWordExtensions(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {

        List<String> words = getWords(status);
        int numberOfWordExtensions = 0;

        for (String word : words) {
            if (isWordExtension(word)) {
                numberOfWordExtensions++;
            }
        }

        return numberOfWordExtensions / normalizer.returnNormalizingValue(status);
    }

    private List<String> getWords(Status status){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\S&&[^,]]+").matcher(status.getText());

        while (m.find()) {
            words.add(m.group());
        }

        return words;
    }

    private boolean isWordExtension(String word) {
        if(isDictionaryWord(word)){
            return false;
        }

        word = word.substring(0, word.length() - 1);
        while(!word.equals("")){
            if(isDictionaryWord(word)){
                return true;
            } else {
                word = word.substring(0, word.length() - 1);
            }
        }

        return false;
    }

    private boolean isDictionaryWord(String word) {
        IWiktionaryEdition wkt = JWKTL.openEdition(new File("/Users/joshuahowell/Desktop/Twitter_Guard/Wiktionary/Dictionary/"));
        IWiktionaryPage page = wkt.getPageForWord(word);

        try {
            page.getEntries();
            return true;
        }catch(Exception ex){
            return false;
        } finally {
            wkt.close();
        }
    }
}
