package Comparators;


import Corpus.*;
import Features.StatusFeature;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Distance implements Comparator<Document> {
    Corpus corpus;
    Document queryDocument;
    Map<StatusFeature, Double> means = new HashMap<StatusFeature, Double>();
    Map<StatusFeature, Double> standardDeviations = new HashMap<StatusFeature, Double>();

    public Distance(Corpus corpus, Document queryDocument){
        this.corpus = corpus;
        this.queryDocument = queryDocument;
        getMeans();
        getStandardDeviations();
    }

    public int compare(Document document1, Document document2) {
        return (int) (delta(document2) - delta(document2));
    }

    private double delta(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.pow((getZScore(statusFeature, queryDocument) - getZScore(statusFeature, document)), 2);
        }

        return zScoreTotal / statusFeatures.size();
    }

    private double getZScore(StatusFeature statusFeature, Document document){
        return (document.getValueForFeature(statusFeature) - means.get(statusFeature)) / standardDeviations.get(statusFeature);
    }


    private void getMeans() {
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        List<Document> documentsInCorpus = corpus.getAllDocuments();

        for(StatusFeature statusFeature : statusFeatures){
            double featureTotal = 0;
            for(Document document : documentsInCorpus){
                featureTotal += document.getValueForFeature(statusFeature);
            }

            means.put(statusFeature, featureTotal / documentsInCorpus.size());
        }
    }

    private void getStandardDeviations(){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        List<Document> documentsInCorpus = corpus.getAllDocuments();

        for(StatusFeature statusFeature : statusFeatures){
            double total = 0;
            for(Document document : documentsInCorpus){
                total += Math.pow(document.getValueForFeature(statusFeature) - means.get(statusFeature), 2);
            }

            standardDeviations.put(statusFeature, Math.sqrt(total / documentsInCorpus.size()));
        }

    }
}
