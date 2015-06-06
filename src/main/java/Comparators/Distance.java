package Comparators;


import Corpus.*;
import Features.StatusFeature;

import java.util.Comparator;
import java.util.List;

public class Distance implements Comparator<Document> {
    ZScoreDistance zScoreDistance;
    Document queryDocument;

    public Distance(Corpus corpus, Document queryDocument){
        new ZScoreDistance(corpus);
        this.queryDocument = queryDocument;
    }

    public int compare(Document document1, Document document2) {
        return (int) (distance(document1) - distance(document2));
    }

    private double distance(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.pow((zScoreDistance.getZScore(statusFeature, queryDocument) - zScoreDistance.getZScore(statusFeature, document)), 2);
        }

        return zScoreTotal / statusFeatures.size();
    }
}
