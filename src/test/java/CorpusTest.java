import org.junit.Before;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.URLEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CorpusTest {

//    private Corpus corpus;
//    private Document knowEverything;
//    private Document afterLife;
//    private Document helloGoodbye;
//
//    private Document testDocument1;
//    private Document testDocument2;
//
//    @Before
//    public void setUp(){
//        corpus = new Corpus();
//        String quote = "People who think they know everything are a great annoyance to those of us who do.";
//        knowEverything = new Document(quote, "Isaac Asimov");
//
//        String another_quote = "I don't believe in an afterlife, so I don't have to spend my whole life fearing hell, or fearing heaven even more. For whatever the tortures of hell, I think the boredom of heaven would be even worse.";
//        afterLife = new Document(another_quote, "Isaac Asimov");
//
//        helloGoodbye = new Document("I don't know why you say goodbye, I say hello hello hello", "The Beatles");
//
//
//        testDocument1 = mock(Document.class);
//        testDocument2 = mock(Document.class);
//
//        when(testDocument1.getVocabulary()).thenReturn(new HashSet<String>(Arrays.asList("hello", "goodbye")));
//        when(testDocument2.getVocabulary()).thenReturn(new HashSet<String>(Arrays.asList("goodbye", "friend")));
//    }
//
//    @Test
//    public void testGetAverageNumberOfWordsForAuthor(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        int expectedAverageForAsimov = 27;
//        int actualAverageForAsimov = corpus.getAverageNumberOfWordsForAuthor("Isaac Asimov");
//
//        assertEquals(expectedAverageForAsimov, actualAverageForAsimov);
//    }
//
//    @Test
//    public void testFile(){
//        File rapeThreats = new File("/Users/joshuahowell/Desktop/Twitter Guard/Corpus/Rape Threats.txt");
//        assertEquals("Rape Threats.txt", rapeThreats.getName());
//    }
//
//    @Test
//    public void testDirectory(){
//        File normalTweetsDirectory = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus/Normal_Tweets/");
//        assertTrue(normalTweetsDirectory.isDirectory());
//        assertEquals(normalTweetsDirectory.getName(), "Normal_Tweets");
//        File[] statuses = normalTweetsDirectory.listFiles(new IgnoreHiddenFilesFilter());
//
//        int numberOfTweets = 200;
//
//        assertEquals(statuses.length, numberOfTweets);
//    }
//
//
//    @Test
//    public void testAddVocabulary(){
//        corpus.addDocument(testDocument1);
//        corpus.addDocument(testDocument2);
//        Set<String> expectedCorpusVocabulary = new HashSet<String>(Arrays.asList("hello", "goodbye", "friend"));
//
//        Set<String> actualCorpusVocabulary = corpus.completeVocabulary();
//
//        assertEquals(expectedCorpusVocabulary, actualCorpusVocabulary);
//
//    }
//
//    @Test
//    public void testTermFrequencyForAuthor(){
//        corpus.addDocument(knowEverything);
//
//        int whoTermFrequency = corpus.termFrequencyForAuthor("who", "Isaac Asimov");
//
//        assertThat(whoTermFrequency, equalTo(2));
//    }
//
//    @Test
//    public void testTermFrequencyForAuthor_WholeIsNotWho(){
//        corpus.addDocument(afterLife);
//
//        int whoTermFrequency = corpus.termFrequencyForAuthor("who", "Isaac Asimov");
//
//        assertThat(whoTermFrequency, equalTo(0));
//    }
//
//    @Test
//    public void whosWho(){
//        corpus.addDocument(new Document("Who's who.", "Isaac Asimov"));
//
//        int whoTermFrequency = corpus.termFrequencyForAuthor("who", "Isaac Asimov");
//
//        assertThat(whoTermFrequency, equalTo(1));
//    }
//
//    @Test
//    public void testTermFrequencyForAuthorOverMultipleDocuments(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//
//        int thinkTermFrequency = corpus.termFrequencyForAuthor("think", "Isaac Asimov");
//
//        assertThat(thinkTermFrequency, is(equalTo(2)));
//    }
//
//    @Test
//    public void testNumberOfWordsForAuthor(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//
//       int numberOfWords = corpus.numberOfWordTokensForAuthor("Isaac Asimov");
//
//       assertThat(numberOfWords, is(equalTo(54)));
//    }
//
//    @Test
//    public void testNumberOfTermsInCorpus(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        int numberOfWords = corpus.numberOfTermsInCorpus();
//
//        assertThat(numberOfWords, is(equalTo(66)));
//    }
//
//    @Test
//    public void testTermFrequencyInCorpus(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        int termFrequencyInCorpus = corpus.termFrequencyInCorpus("I");
//
//        assertThat(termFrequencyInCorpus, is(equalTo(5)));
//    }
//
//
//    @Test
//    public void testStringSplit(){
//        Matcher m = Pattern.compile("[\\w'-]+").matcher("Who's who state-of-the-art.");
//        List<String> words = new ArrayList<String>();
//
//        while(m.find()) {
//           words.add(m.group());
//        }
//
//        assertEquals(words.size(), 3);
//    }
//
//    @Test
//    public void testLidStoneSmoothing(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        double expectedSmoothedFrequency = 0.0452904002407;
//
//        double actualSmoothedFrequency = corpus.lidstoneSmoothing("hello", .01);
//
//        assertEquals(expectedSmoothedFrequency, actualSmoothedFrequency, .01);
//    }
//
//    @Test
//    public void testTermSpecificity(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//        double expectedTermSpecificity = 3.41028055011;
//
//        double actualTermSpecificity = corpus.termSpecificity("hello", "The Beatles");
//
//        assertEquals(expectedTermSpecificity, actualTermSpecificity, .01);
//    }
//
//    @Test
//    public void testGetAuthorsSpecificTerms(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//        Set<String> expectedSpecificTerms = new HashSet<String>(Arrays.asList("say", "hello"));
//
//        Set<String> actualSpecificTerms = corpus.getAuthorsSpecificTerms("The Beatles");
//
//        assertEquals(expectedSpecificTerms, actualSpecificTerms);
//    }
//
//    @Test
//    public void testNumberOfDocumentsForAuthor(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        int numberOfAsimovDocuments = 2;
//        int numberOfBeatlesDocuments = 1;
//
//        assertEquals(numberOfAsimovDocuments, corpus.numberOfDocumentsForAuthor("Isaac Asimov"));
//        assertEquals(numberOfBeatlesDocuments, corpus.numberOfDocumentsForAuthor("The Beatles"));
//    }
//
//    @Test
//    public void testNumberOfDocuments(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//        corpus.addDocument(helloGoodbye);
//
//        assertEquals(corpus.numberOfDocuments(), 3);
//    }
//
//    @Test
//    public void testGenerateAuthorFromFile(){
//        corpus.addDocument(knowEverything);
//        corpus.addDocument(afterLife);
//
//        Corpus asimovCorpus = new Corpus();
//        asimovCorpus.generateAuthorandDocumentsFromTextFile("/Users/joshuahowell/Desktop/Twitter_Guard/app/src/test/java/Isaac Asimov.txt");
//
//        assertEquals(corpus, asimovCorpus);
//    }
//
//    @Test
//    public void testGenerateAuthorFromDirectory(){
//        Corpus actualCorpus = new Corpus();
//        actualCorpus.generateAuthorFromDirectory(new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus/Test_Tweets/"));
//
//        Corpus expectedCorpus = new Corpus();
//        String author = "Test_Tweets";
//        File statusFile1 = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus/Test_Tweets/status1.ser");
//        File statusFile2 = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus/Test_Tweets/status2.ser");
//
//        String status1 = null, status2 = null;
//
//        try {
//            ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream(statusFile1));
//            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(statusFile2));
//
//            status1 = (String)ois1.readObject();
//            status2 = (String)ois2.readObject();
//
//        }catch(FileNotFoundException ex){
//            System.out.println("FileNotFoundException");
//        }catch(ClassNotFoundException ex){
//            System.out.println("ClassNotFoundException!");
//        }catch(IOException ex){
//            System.out.println("IOException!");
//        }
//
//        expectedCorpus.addDocument(new Document(status1, author));
//        expectedCorpus.addDocument(new Document(status2, author));
//
//        assertEquals(actualCorpus, expectedCorpus);
//    }
//
//    @Test
//    public void testGetAverageNumberOfURLsPerTweetForAuthor(){
//        Status twoEntityStatus = mock(Status.class);
//        when(twoEntityStatus.getText()).thenReturn("");
//        URLEntity[] twoEntities = {mock(URLEntity.class), mock(URLEntity.class)};
//        when(twoEntityStatus.getURLEntities()).thenReturn(twoEntities);
//        Document twoEntityDocument = new Document(twoEntityStatus, "URL User");
//
//        Status threeEntityStatus = mock(Status.class);
//        when(threeEntityStatus.getText()).thenReturn("");
//        URLEntity[] threeEntities = {mock(URLEntity.class), mock(URLEntity.class), mock(URLEntity.class)};
//        when(threeEntityStatus.getURLEntities()).thenReturn(threeEntities);
//        Document threeEntityDocument = new Document(threeEntityStatus, "URL User");
//
//        Status zeroEntityStatus = mock(Status.class);
//        when(zeroEntityStatus.getText()).thenReturn("");
//        when(zeroEntityStatus.getURLEntities()).thenReturn(new URLEntity[]{});
//        Document zeroEntityDocument = new Document(zeroEntityStatus, "URL User");
//
//        corpus.addDocument(twoEntityDocument);
//        corpus.addDocument(threeEntityDocument);
//        corpus.addDocument(zeroEntityDocument);
//
//        assertEquals(5/3.0, corpus.getAverageNumberOfURLsPerTweetForAuthor("URL User"), 0);
//    }


}