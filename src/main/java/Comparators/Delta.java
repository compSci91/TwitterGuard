package Comparators;

import Features.StatusFeature;


import java.util.Comparator;
import java.util.List;
import Corpus.Document;

public class Delta implements Comparator<List<Document>> {
    Document queryDocument;

    public Delta(Document queryDocument){
        this.queryDocument = queryDocument;
    }

    public int compare(List<Document> authorProfile1, List<Document> authorProfile2) {
        return 0;
    }

    public double getZScore(StatusFeature statusFeature, List<Document> authorProfile){
        double documentValue = queryDocument.getValueForFeature(statusFeature);
        double mean = getMean(statusFeature, authorProfile);
        double standardDeviation = getStandardDeviation(statusFeature, authorProfile);

        return (documentValue - mean) / standardDeviation;
    }

    private double getStandardDeviation(StatusFeature statusFeature, List<Document> authorProfile) {
        return 0;
    }

    private double getMean(StatusFeature statusFeature, List<Document> authorProfile) {
        return 0;
    }
}
