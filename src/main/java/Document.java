import twitter4j.Status;

import Features.*;
import Features.Normalizers.*;

public class Document {

    private Status status;
    public final String AUTHOR;
    public final double NUMBER_OF_WORDS;
    public final double NUMBER_OF_SENTENCES;
    public final double AVERAGE_NUMBER_OF_WORDS_PER_SENTENCE;
    public final double USE_OF_QUOTATIONS;
    public final double PERCENTAGE_OF_WORDS_WITH_ALL_CAPITAL_LETTERS;
    public final double NUMBER_OF_SPECIAL_CHARACTERS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double NUMBER_OF_BEGINNING_OF_SENTENCE_WORDS_NORMALIZED_BY_NUMBER_OF_SENTENCES;
    public final double NUMBER_OF_DIGITS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double NUMBER_OF_PUNCTUATIONS_PER_SENTENCE;
    public final double NUMBER_OF_DICTIONARY_WORDS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double AVERAGE_WORD_LENGTH;
    public final double NUMBER_OF_WORD_EXTENSIONS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double NUMBER_OF_HASHTAGS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double NUMBER_OF_USER_MENTIONS_NORMALIZED_BY_NUMBER_OF_WORDS;
    public final double NUMBER_OF_URLS;

    public Document(Status status, String author){
        Normalizer numberOfSentencesNormalizer = new NumberOfSentencesNormalizer();
        Normalizer numberOfWordsNormalizer = new NumberOfWordsNormalizer();

        this.status = status;
        AUTHOR = author;
        NUMBER_OF_WORDS = new NumberOfWords().returnValue(status);
        NUMBER_OF_SENTENCES = new NumberOfSentences().returnValue(status);
        AVERAGE_NUMBER_OF_WORDS_PER_SENTENCE = new NumberOfWords(numberOfSentencesNormalizer).returnValue(status);
        USE_OF_QUOTATIONS = new UseOfQuotations().returnValue(status);
        PERCENTAGE_OF_WORDS_WITH_ALL_CAPITAL_LETTERS = new NumberOfWordsWithAllCapitalLettersFeature(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_SPECIAL_CHARACTERS_NORMALIZED_BY_NUMBER_OF_WORDS = new NumberOfSpecialCharacters(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_BEGINNING_OF_SENTENCE_WORDS_NORMALIZED_BY_NUMBER_OF_SENTENCES = new NumberOfBeginningOfSentenceWordsCapitalized(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_DIGITS_NORMALIZED_BY_NUMBER_OF_WORDS = new NumberOfDigits(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_PUNCTUATIONS_PER_SENTENCE = new NumberOfPunctuations(numberOfSentencesNormalizer).returnValue(status);
        NUMBER_OF_DICTIONARY_WORDS_NORMALIZED_BY_NUMBER_OF_WORDS = new NumberOfDictionaryWords(numberOfWordsNormalizer).returnValue(status);
        AVERAGE_WORD_LENGTH = new TotalWordLength(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_WORD_EXTENSIONS_NORMALIZED_BY_NUMBER_OF_WORDS = new NumberOfWordExtensions(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_HASHTAGS_NORMALIZED_BY_NUMBER_OF_WORDS  = new NumberOfHashtags(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_USER_MENTIONS_NORMALIZED_BY_NUMBER_OF_WORDS = new NumberOfUserMentions(numberOfWordsNormalizer).returnValue(status);
        NUMBER_OF_URLS = new NumberOfURLs().returnValue(status);
    }
}
