import org.junit.Test;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DocumentTest {
    @Test
   public void testGetNumberOfSentencesNoQuoationMarks(){
       Document twoSentences = new Document("There are 2.0 sentences here. Not 3.", "no author");
       assertEquals(2, twoSentences.getNumberOfSentences());
   }

    @Test
    public void testGetNumberOfSentencesWithQuoationMarks(){
        Document twoSentences = new Document("\"There are 2.0 sentences here.\" Not 3.", "no author");
        assertEquals(2, twoSentences.getNumberOfSentences());
    }

    @Test
    public void testGetNumberOfSentencesWithApostrophe(){
        Document twoSentences = new Document("There are two sentences here. Don't deny it.", "no author");
        assertEquals(2, twoSentences.getNumberOfSentences());
    }

    @Test
    public void testGetVocabulary() throws Exception {
        Document document = new Document("I don't know why you say goodbye, I say hello hello hello.", "The Beatles");
        Set<String> expectedVocabulary = new HashSet<String>();
        expectedVocabulary.add("I");
        expectedVocabulary.add("don't");
        expectedVocabulary.add("know");
        expectedVocabulary.add("why");
        expectedVocabulary.add("you");
        expectedVocabulary.add("say");
        expectedVocabulary.add("goodbye");
        expectedVocabulary.add("hello");

        Set<String> returnedVocabulary = document.getVocabulary();

        assertEquals(expectedVocabulary, returnedVocabulary);

    }

    @Test
    public void testGetPercentageOfWordsWithAllCapitalLetters() {
        Document twoWordsAllCaps = new Document("THERE are TWO all cap words", "no author");
        assertEquals(twoWordsAllCaps.getPercentageOfWordsWithAllCapitalLetters(), 1/3.0, 0);
    }

    @Test
    public void testGetNumberOfDigitsNormalizedByNumberOfWords(){
        Document twoDigts = new Document("There are 2.0 digits here.", "no author");
        assertEquals(2/5.0, twoDigts.getNumberOfDigitsNormalizedByNumberOfWords(), 0);
    }

    @Test
    public void testGetAverageWordLength(){
        Document tinySentence = new Document("The tiny sentence", "no author");
        assertEquals(tinySentence.getAverageWordLength(), 5, 0);
    }

    @Test
    public void testGetNumberOfHashtagsNormalizedByWords(){
        Status twoHashtagStatus = mock(Status.class);
        when(twoHashtagStatus.getHashtagEntities()).thenReturn(new HashtagEntity[]{mock(HashtagEntity.class), mock(HashtagEntity.class)});
        when(twoHashtagStatus.getText()).thenReturn("#TwitterGuard for the win.");
        Document twoHashtagDocument = new Document(twoHashtagStatus, "no author");
        assertEquals(2/4.0, twoHashtagDocument.getNumberOfHashtagsNormalizedByWords(), 0);
    }

    @Test
    public void testGetNumberOfWords() throws Exception {
        Document document = new Document("I don't know why you say goodbye, I say hello hello hello.", "The Beatles");

        int expectedNumberOfWords = 12;
        assertEquals(document.getNumberOfWords(), expectedNumberOfWords);
    }

    @Test
    public void testContainsQuotations(){
        Document documentContainsQuotations = new Document("\"Good evening,\", said the clergymen.", "no author");
        Document documentDoesNotContainQuotations = new Document("hello goodbye", "no author");

        assertEquals(1, documentContainsQuotations.containsQuotations());
        assertEquals(0, documentDoesNotContainQuotations.containsQuotations());
    }

    @Test
    public void testGetNumberOfUserMentionsNormalizedByNumberOfWords(){
        Status oneUserMentionStatus = mock(Status.class);
        when(oneUserMentionStatus.getUserMentionEntities()).thenReturn(new UserMentionEntity[]{mock(UserMentionEntity.class)});
        when(oneUserMentionStatus.getText()).thenReturn("@Josh Howell is programming Twitter Guard");
        Document oneUserMention = new Document(oneUserMentionStatus, "no author");
        assertEquals(1/6.0, oneUserMention.getNumberOfUserMentionsNormalizedByNumberWords(), 0);
    }


    @Test
    public void testGetNumberOfSpecialCharactersNormalizedByNumberOfWords(){
        Document twoSpecialCharacters = new Document("↪ Ⓐ three random words","no author");
        assertEquals(2/5.0, twoSpecialCharacters.getNumberOfSpecialCharactersNormalizedByNumberOfWords(), 0);
    }

    @Test
    public void testGetAverageNumberOfWordsPerSentence(){
        Document averageOfFour = new Document("A simple sentence. A more complex sentence. A still more complex sentence.", "no author");
        assertEquals(4.0, averageOfFour.getAverageNumberOfWordsPerSentence(), 0);
    }

    @Test
    public void testGetNumberOfBeginningOfSentenceWordsCapitalizedNormalizedByNumberOfSentences(){
        Document twoOfFive = new Document("nope. Yes. nope. nope. Yes.", "no author");
        assertEquals(2/5.0, twoOfFive.getNumberOfBeginningOfSentenceWordsCapitalizedNormalizedByNumberOfSentences(), 0);
    }

    @Test
    public void testGetNumberOfPunctuationsNormalizedByNumberOfSentences(){
        Document threePunctuation = new Document("There are two sentences here. Don't deny it.", "no author");
        assertEquals(3 / 2.0, threePunctuation.getNumberOfPunctuationsNormalizedByNumberOfSentences(), 0);
    }

    @Test
    public void testGetNumberOfURLs(){
        Status twoEntityStatus = mock(Status.class);
        URLEntity[] twoEntities = {mock(URLEntity.class), mock(URLEntity.class)};
        when(twoEntityStatus.getURLEntities()).thenReturn(twoEntities);
        Document twoEntityDocument = new Document(twoEntityStatus, "URL User");

        assertEquals(2, twoEntityDocument.getNumberOfURLs());
    }

    @Test
    public void testGetNumberOfDictionaryWordsNormalizedByNumberOfWords(){
        Status twoWordStatus = mock(Status.class);
        when(twoWordStatus.getText()).thenReturn("two words lsjkdf ljskfd");
        Document twoWordDocument = new Document(twoWordStatus, "no author");

        assertEquals(2/4.0, twoWordDocument.getNumberOfDictionaryWordsNormalizedByNumberOfWords(), 0);
    }

    @Test
    public void testGetNumberOfWordExtentionsNormalizedByNumberOfWords(){
        Status twoWordExtensionStatus = mock(Status.class);
        when(twoWordExtensionStatus.getText()).thenReturn("two words Hiii Byeee");
        Document twoWordDocument = new Document(twoWordExtensionStatus, "no author");

        assertEquals(2 / 4.0, twoWordDocument.getNumberOfWordExtensionsNormalizedByNumberOfWords(), 0);

    }
}