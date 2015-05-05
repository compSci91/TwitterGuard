import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    private String text, author;


    public Document(String text, String author){
        this.text = text;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public Set<String> getVocabulary(){
        return new HashSet<String>(getWords());
    }

    private List<String> getWords(){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\S&&[^,]]+").matcher(text);

        while (m.find()) {
            words.add(m.group());
        }

        return words;
    }

    public int getNumberOfWords(){
        return getWords().size();
    }

    public double getPercentageOfWordsWithAllCapitalLetters(){
        List<String> words = getWords();
        int numberOfWordsWithAllCapitalLetters = 0;

        for(String word : words){
            if(word.equals(word.toUpperCase())){
                numberOfWordsWithAllCapitalLetters++;
            }
        }

        return numberOfWordsWithAllCapitalLetters / (double)getNumberOfWords();
    }

    public int getTermFrequency(String term) {
        int termFrequency = 0;

        List<String> words = getWords();

        for (String word : words) {
            if (word.equals(term)) {
                termFrequency++;
            }
        }
        return termFrequency;
    }

    public int getNumberOfSentences() {
        return getSentences().size();
    }

    public List<String> getSentences() {
        String[] sentences = text.split("(?<=[.?!])[\\s\"]+(?=[a-zA-Z])");
       return Arrays.asList(sentences);
    }

    public double getAverageNumberOfWordsPerSentence() {
        return getNumberOfWords()/ (double)getNumberOfSentences();
    }

    public double getNumberOfDigitsNormalizedByNumberOfWords() {
        int numberOfDigits = 0;

        for(int i = 0; i < text.length(); i++){
            if(Character.isDigit(text.charAt(i))){
                numberOfDigits++;
            }
        }

        return numberOfDigits / (double) getNumberOfWords();
    }

    public double getAverageWordLength() {
        List<String> words = getWords();
        int totalWordLength = 0;

        for(String word : words){
            totalWordLength += word.length();
        }

        return totalWordLength / (double)getNumberOfWords();
    }

    public double getNumberOfHashtagsNormalizedByWords() {
        List<String> words = getWords();
        int totalNumberOfHashtags = 0;

        for(String word : words){
            if(word.charAt(0) == '#'){
                totalNumberOfHashtags++;
            }
        }

        return totalNumberOfHashtags / (double) getNumberOfWords();
    }

    public int containsQuotations() {
        if(text.indexOf('"') == text.lastIndexOf('"')){
            return 0;
        }else{
            return 1;
        }
    }

    public double getNumberOfUserMentionsNormalizedByNumberWords() {
        List<String> words = getWords();
        int numberOfUserMentions = 0;

        for(String word : words){
            if(isUserMention(word)){
                numberOfUserMentions++;
            }
        }
        return numberOfUserMentions / (double)getNumberOfWords();
    }

    private boolean isUserMention(String word) {
        return word.length() != 1 &&
                word.lastIndexOf('@') == 0;

    }

    public double getNumberOfSpecialCharactersNormalizedByNumberOfWords() {
        int numberOfSpecialCharacters = 0;

        for(int i = 0; i < text.length(); i++){
            if(isSpecialCharacter(text.charAt(i))){
                numberOfSpecialCharacters++;
            }
        }

        return numberOfSpecialCharacters / (double)getNumberOfWords();
    }

    private boolean isSpecialCharacter(char character) {
        String keyboardCharacters = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";

        return !Character.isLetterOrDigit(character) &&
                !Character.isWhitespace(character) &&
                 keyboardCharacters.indexOf(character) == -1 ;
    }

    public double getNumberOfBeginningOfSentenceWordsCapitalizedNormalizedByNumberOfSentences() {
        List<String> sentences = getSentences();
        int numberOfBeginningOfSentenceWordsCapitalized = 0;

        for(String sentence : sentences){
            if(Character.isUpperCase(sentence.charAt(0))){
                numberOfBeginningOfSentenceWordsCapitalized++;
            }
        }
        return numberOfBeginningOfSentenceWordsCapitalized / (double)getNumberOfSentences();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (text != null ? !text.equals(document.text) : document.text != null) return false;
        return !(author != null ? !author.equals(document.author) : document.author != null);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    public double getNumberOfPunctuationsNormalizedByNumberOfSentences() {
        Matcher m = Pattern.compile("\\p{Punct}").matcher(text);
        int numberOfPunctuation = 0;

        while (m.find()) {
            numberOfPunctuation++;
            m.group();
        }

        return numberOfPunctuation / (double)getNumberOfSentences();
    }
}
