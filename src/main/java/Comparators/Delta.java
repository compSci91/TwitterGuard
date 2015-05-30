package Comparators;

import Features.StatusFeature;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Corpus.*;

public class Delta implements Comparator<Document> {
    Corpus corpus;
    Document queryDocument;

    public Delta(Corpus corpus, Document queryDocument){
        this.corpus = corpus;
        this.queryDocument = queryDocument;
    }

    public int compare(Document document1, Document document2) {
        return (int) (delta(document2) - delta(document2));
    }

    private double delta(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.abs((getZScore(statusFeature, queryDocument) - getZScore(statusFeature, document)));
        }

        return zScoreTotal / statusFeatures.size();
    }

    private double getZScore(StatusFeature statusFeature, Document document){
        return (document.getValueForFeature(statusFeature) - corpus.getMean(statusFeature)) / corpus.getStandardDeviation(statusFeature);
    }
}
