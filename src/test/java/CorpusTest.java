import Corpus.*;
import Features.NumberOfSentences;
import Features.NumberOfWords;
import Features.StatusFeature;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;


public class CorpusTest {
    Corpus corpus;
    StatusFeature numberOfWords = new NumberOfWords();
    StatusFeature numberOfSentences = new NumberOfSentences();

    @Before
    public void setUp(){
        Document document1 = mock(Document.class);
        when(document1.getStatusFeatures()).thenReturn(new ArrayList<StatusFeature>(
                Arrays.asList(numberOfWords, numberOfSentences)
        ));

        when(document1.getValueForFeature(numberOfWords)).thenReturn(11.0);
        when(document1.getValueForFeature(numberOfSentences)).thenReturn(15.0);

        Document document2 = mock(Document.class);
        when(document2.getValueForFeature(numberOfWords)).thenReturn(4.0);
        when(document2.getValueForFeature(numberOfSentences)).thenReturn(2.0);


        Document document3 = mock(Document.class);
        when(document3.getValueForFeature(numberOfWords)).thenReturn(3.0);
        when(document3.getValueForFeature(numberOfSentences)).thenReturn(4.0);

        corpus = new Corpus(new ArrayList<Document>(
                Arrays.asList(document1, document2, document3)
        ));
    }

    @Test
    public void testGetMean() {
        assertEquals(6, corpus.getMean(numberOfWords), 0);
        assertEquals(7, corpus.getMean(numberOfSentences), 0);
    }

    @Test
    public void testGetStandardDeviation(){
        assertEquals(3.5590, corpus.getStandardDeviation(numberOfWords),  .0001);
        assertEquals(5.7154, corpus.getStandardDeviation(numberOfSentences),  .0001);
    }
}
//    private Corpus.Corpus corpus;
//    private Corpus.Corpus.Document knowEverything;
//    private Corpus.Corpus.Document afterLife;
//    private Corpus.Corpus.Document helloGoodbye;
//
//    private Corpus.Corpus.Document testDocument1;
//    private Corpus.Corpus.Document testDocument2;
//
//    @Before
//    public void setUp(){
//        corpus = new Corpus.Corpus();
//        String quote = "People who think they know everything are a great annoyance to those of us who do.";
//        knowEverything = new Corpus.Corpus.Document(quote, "Isaac Asimov");
//
//        String another_quote = "I don't believe in an afterlife, so I don't have to spend my whole life fearing hell, or fearing heaven even more. For whatever the tortures of hell, I think the boredom of heaven would be even worse.";
//        afterLife = new Corpus.Corpus.Document(another_quote, "Isaac Asimov");
//
//        helloGoodbye = new Corpus.Corpus.Document("I don't know why you say goodbye, I say hello hello hello", "The Beatles");
//
//
//        testDocument1 = mock(Corpus.Corpus.Document.class);
//        testDocument2 = mock(Corpus.Corpus.Document.class);
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
//        File rapeThreats = new File("/Users/joshuahowell/Desktop/Twitter Guard/Corpus.Corpus/Rape Threats.txt");
//        assertEquals("Rape Threats.txt", rapeThreats.getName());
//    }
//
//    @Test
//    public void testDirectory(){
//        File normalTweetsDirectory = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/Normal_Tweets/");
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
//        corpus.addDocument(new Corpus.Corpus.Document("Who's who.", "Isaac Asimov"));
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
//        Corpus.Corpus asimovCorpus = new Corpus.Corpus();
//        asimovCorpus.generateAuthorandDocumentsFromTextFile("/Users/joshuahowell/Desktop/Twitter_Guard/app/src/test/java/Isaac Asimov.txt");
//
//        assertEquals(corpus, asimovCorpus);
//    }
//
//    @Test
//    public void testGenerateAuthorFromDirectory(){
//        Corpus.Corpus actualCorpus = new Corpus.Corpus();
//        actualCorpus.generateAuthorFromDirectory(new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/Test_Tweets/"));
//
//        Corpus.Corpus expectedCorpus = new Corpus.Corpus();
//        String author = "Test_Tweets";
//        File statusFile1 = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/Test_Tweets/status1.ser");
//        File statusFile2 = new File("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/Test_Tweets/status2.ser");
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
//        expectedCorpus.addDocument(new Corpus.Corpus.Document(status1, author));
//        expectedCorpus.addDocument(new Corpus.Corpus.Document(status2, author));
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
//        Corpus.Corpus.Document twoEntityDocument = new Corpus.Corpus.Document(twoEntityStatus, "URL User");
//
//        Status threeEntityStatus = mock(Status.class);
//        when(threeEntityStatus.getText()).thenReturn("");
//        URLEntity[] threeEntities = {mock(URLEntity.class), mock(URLEntity.class), mock(URLEntity.class)};
//        when(threeEntityStatus.getURLEntities()).thenReturn(threeEntities);
//        Corpus.Corpus.Document threeEntityDocument = new Corpus.Corpus.Document(threeEntityStatus, "URL User");
//
//        Status zeroEntityStatus = mock(Status.class);
//        when(zeroEntityStatus.getText()).thenReturn("");
//        when(zeroEntityStatus.getURLEntities()).thenReturn(new URLEntity[]{});
//        Corpus.Corpus.Document zeroEntityDocument = new Corpus.Corpus.Document(zeroEntityStatus, "URL User");
//
//        corpus.addDocument(twoEntityDocument);
//        corpus.addDocument(threeEntityDocument);
//        corpus.addDocument(zeroEntityDocument);
//
//        assertEquals(5/3.0, corpus.getAverageNumberOfURLsPerTweetForAuthor("URL User"), 0);
//    }
