package Helpers;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryPage;
import twitter4j.Status;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsHelper {
    public List<String> getWords(Status status){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\S&&[^,]]+").matcher(status.getText());

        while (m.find()) {
            String scrubbedWord = scrubWord(m.group());
            words.add(scrubbedWord);
        }

        return words;
    }

    private String scrubWord(String dirtyWord){
        String scrubbedWord = dirtyWord;

        if(isDictionaryWord(scrubbedWord)){
            return scrubbedWord;
        }

        while(scrubbedWord.charAt(scrubbedWord.length()-1) == '.'){
            scrubbedWord = scrubbedWord.substring(0, scrubbedWord.length()-1);
            if(isDictionaryWord(scrubbedWord)){
                return scrubbedWord;
            }
        }

        return scrubbedWord;
    }

    public boolean isDictionaryWord(String word) {
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

    public boolean isWordExtension(String word) {
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
}
