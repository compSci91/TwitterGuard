package Corpus;

import java.util.*;
import Features.StatusFeature;

public class Corpus implements java.io.Serializable {

    List<Document> documents;
    Map<StatusFeature, Double> means;
    Map<StatusFeature, Double> standardDeviations;

    public Corpus(List<Document> documents){
        this.documents = documents;
        means = getMeans();
        standardDeviations = getStandardDeviations();
    }

    private Map<StatusFeature, Double> getMeans() {
        Map<StatusFeature, Double> means = new HashMap<StatusFeature, Double>();
        List<StatusFeature> statusFeatures = documents.get(0).getStatusFeatures();

        for(StatusFeature statusFeature : statusFeatures){
            double featureTotal = 0;
            for(Document document : documents){
                featureTotal += document.getValueForFeature(statusFeature);
            }

            means.put(statusFeature, featureTotal / documents.size());
        }

        return means;
    }

    private Map<StatusFeature, Double> getStandardDeviations(){
        Map<StatusFeature, Double> standardDeviations = new HashMap<StatusFeature, Double>();
        List<StatusFeature> statusFeatures = documents.get(0).getStatusFeatures();

        for(StatusFeature statusFeature : statusFeatures){
            double total = 0;
            for(Document document : documents){
                total += Math.pow(document.getValueForFeature(statusFeature) - getMean(statusFeature), 2);
            }

            standardDeviations.put(statusFeature, Math.sqrt(total / documents.size()));
        }

        return standardDeviations;
    }

    public double getMean(StatusFeature statusFeature){
        return means.get(statusFeature);
    }

    public double getStandardDeviation(StatusFeature statusFeature){
        return standardDeviations.get(statusFeature);
    }


//   private HashMap<String, ArrayList<Corpus.Corpus.Document>> corpus;
//   private Set<String> completeVocabulary;
//   public List<String> statusTexts;
//
//    public Corpus.Corpus(){
//       corpus = new HashMap<String, ArrayList<Corpus.Corpus.Document>>();
//       completeVocabulary = new HashSet<String>();
//        statusTexts = new ArrayList<String>();
//    }
//
//    public void generateAuthorandDocumentsFromTextFile(String fileName) {
//        generateAuthorandDocumentsFromTextFile(new File(fileName));
//    }
//
//    private void generateAuthorandDocumentsFromTextFile(File file){
//        String author = file.getName();
//        author = author.substring(0, author.lastIndexOf('.'));
//
//        Scanner reader = null;
//
//        try {
//            reader = new Scanner(file);
//        }catch(FileNotFoundException ex){
//            System.out.println(ex.getMessage());
//        }
//
//        while(reader.hasNextLine()){
//            String text = reader.nextLine();
//            Corpus.Corpus.Document document = new Corpus.Corpus.Document(text, author);
//            addDocument(document);
//        }
//    }
//
//    public int numberOfDocuments(){
//        int numberOfDocuments = 0;
//        for (String author : corpus.keySet()){
//            numberOfDocuments += numberOfDocumentsForAuthor(author);
//        }
//
//        return numberOfDocuments;
//    }
//
//    public Set completeVocabulary(){
//        return completeVocabulary;
//    }
//
//    public void addDocument(Corpus.Corpus.Document document){
//        addVocabulary(document);
//        addDocumentToCorpus(document);
//    }
//
//    private void addDocumentToCorpus(Corpus.Corpus.Document document) {
//        String author = document.getAuthor();
//
//        if(corpus.containsKey(author)){
//            ArrayList<Corpus.Corpus.Document> authorsDocuments = corpus.get(author);
//            authorsDocuments.add(document);
//            corpus.put(author, authorsDocuments);
//        } else{
//            ArrayList<Corpus.Corpus.Document> newAuthor = new ArrayList<Corpus.Corpus.Document>();
//            newAuthor.add(document);
//            corpus.put(author, newAuthor);
//        }
//    }
//
//    private void addVocabulary(Corpus.Corpus.Document document){
//        completeVocabulary.addAll(document.getVocabulary());
//    }
//
//    public int numberOfDocumentsForAuthor(String author){
//        return corpus.get(author).size();
//    }
//
//    public int numberOfTermsInCorpus(){
//        int numberOfTerms = 0;
//
//        for (String author : corpus.keySet()){
//            numberOfTerms += numberOfWordTokensForAuthor(author);
//        }
//
//        return numberOfTerms;
//    }
//
//    public int termFrequencyInCorpus(String term){
//        int termFrequency = 0;
//
//        for (String author : corpus.keySet()){
//            termFrequency += termFrequencyForAuthor(term, author);
//        }
//
//        return termFrequency;
//    }
//
//    public int termFrequencyForAuthor(String term, String author){
//        int termFrequency = 0;
//        ArrayList<Corpus.Corpus.Document> authorsDocuments = corpus.get(author);
//
//        for(Corpus.Corpus.Document document : authorsDocuments){
//            termFrequency += document.getTermFrequency(term);
//        }
//
//        return termFrequency;
//    }
//
//    public int numberOfWordTokensForAuthor(String author){
//        int numberOfWords = 0;
//        ArrayList<Corpus.Corpus.Document> authorsDocuments = corpus.get(author);
//
//        for(Corpus.Corpus.Document document : authorsDocuments){
//            numberOfWords += document.getNumberOfWords();
//        }
//
//        return numberOfWords;
//    }
//
//    public double lidstoneSmoothing(String term, double lambda){
//        return (termFrequencyInCorpus(term) + lambda) / (numberOfTermsInCorpus() + lambda * completeVocabulary.size());
//    }
//
//    public double termSpecificity(String term, String author){
//        double termFrequency = termFrequencyForAuthor(term, author);
//        int numberOfTerms = numberOfWordTokensForAuthor(author);
//        double lambda = .01;
//        double relativeTermFrequencyInCorpus = lidstoneSmoothing(term, lambda);
//
//        return (termFrequency - numberOfTerms * relativeTermFrequencyInCorpus) / Math.sqrt(numberOfTerms * relativeTermFrequencyInCorpus * (1 - relativeTermFrequencyInCorpus));
//    }
//
//    public Set<String> getAuthorsSpecificTerms(String author){
//        Set<String> authorsSpecificTerms = new HashSet<String>();
//        Set<String> authorsVocabulary = getAuthorsVocabulary(author);
//
//        for(String word : authorsVocabulary){
//            if(termSpecificity(word, author) > 2) {
//                authorsSpecificTerms.add(word);
//            }
//        }
//
//        return authorsSpecificTerms;
//    }
//
//    private Set<String> getAuthorsVocabulary(String author) {
//        Set<String> authorsVocabulary = new HashSet<String>();
//        List<Corpus.Corpus.Document> authorsDocuments = corpus.get(author);
//
//        for(Corpus.Corpus.Document document : authorsDocuments){
//            authorsVocabulary.addAll(document.getVocabulary());
//        }
//
//        return authorsVocabulary;
//    }
//
//    public List<Corpus.Corpus.Document> getDocumentsByAuthor(String author){
//        return corpus.get(author);
//    }
//
//
//    public void generateAuthorFromDirectory(File directory) {
//        String author = directory.getName();
//        File[] files = directory.listFiles(new IgnoreHiddenFilesFilter());
//
//        for(File file : files){
//            String statusText = null;
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//                 statusText = (String) ois.readObject();
//            }catch(FileNotFoundException ex){
//                System.out.println("FileNotFoundException");
//            }catch(ClassNotFoundException ex){
//                System.out.println("ClassNotFoundException!");
//            }catch(IOException ex){
//                System.out.println("IOException!");
//            }
//
//            addDocument(new Corpus.Corpus.Document(statusText, author));
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Corpus.Corpus corpus1 = (Corpus.Corpus) o;
//
//        if (!corpus.equals(corpus1.corpus)) return false;
//
//        return true;
//    }
//
//    public int getAverageNumberOfWordsForAuthor(String author) {
//
//        List<Corpus.Corpus.Document> documents = corpus.get(author);
//        int totalNumberOfWords = 0;
//
//        for(Corpus.Corpus.Document document : documents){
//            System.out.println(document.getNumberOfWords());
//            totalNumberOfWords += document.getNumberOfWords();
//        }
//
//        System.out.println(totalNumberOfWords);
//        int averageNumberOfWords = totalNumberOfWords / documents.size();
//
//        return averageNumberOfWords;
//
//    }
//
//    public double getAverageNumberOfURLsPerTweetForAuthor(String author) {
//        List<Corpus.Corpus.Document> documents = getDocumentsByAuthor(author);
//        int numberOfURLs = 0;
//
//        for(Corpus.Corpus.Document document : documents){
//            numberOfURLs += document.getNumberOfURLs();
//        }
//
//        return numberOfURLs / (double)documents.size();
//    }
}
