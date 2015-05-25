import twitter4j.Status;

import Features.*;
import Features.Normalizers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

    private Map<StatusFeature, Double> documentFeatures;
    List<StatusFeature> statusFeatures;

    private Status status;
    public final String AUTHOR;

    public Document(Status status, String author){
        documentFeatures = new HashMap<StatusFeature, Double>();
        statusFeatures = new ArrayList<StatusFeature>();
        Normalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();

        this.status = status;
        AUTHOR = author;

        statusFeatures.add(new NumberOfWords());
        statusFeatures.add(new NumberOfSentences());
        statusFeatures.add(new NumberOfWords(numberOfSentencesNormalizer));
        statusFeatures.add(new UseOfQuotations());
        statusFeatures.add(new NumberOfWordsWithAllCapitalLettersFeature(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfSpecialCharacters(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfBeginningOfSentenceWordsCapitalized(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfDigits(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfPunctuations(numberOfSentencesNormalizer));
        statusFeatures.add(new NumberOfDictionaryWords(numberOfWordsNormalizer));
        statusFeatures.add(new TotalWordLength(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfWordExtensions(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfHashtags(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfUserMentions(numberOfWordsNormalizer));
        statusFeatures.add(new NumberOfURLs());

        for(StatusFeature statusFeature : statusFeatures){
            documentFeatures.put(statusFeature, new Double(statusFeature.returnValue(status)));
        }
    }
}
