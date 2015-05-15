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

public class NumberOfDictionaryWordsFeature implements StatusFeature{
    Normalizer normalizer;

    public NumberOfDictionaryWordsFeature(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfDictionaryWordsFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }


    public double returnValue(Status status) {
        List<String> words = getWords(status);
        int numberOfDictionaryWords = 0;

        for(String word : words){
            if(isDictionaryWord(word)){
                numberOfDictionaryWords++;
            }
        }

        return numberOfDictionaryWords / normalizer.returnNormalizingValue(status);
    }

    private List<String> getWords(Status status){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\S&&[^,]]+").matcher(status.getText());

        while (m.find()) {
            words.add(m.group());
        }

        return words;
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
