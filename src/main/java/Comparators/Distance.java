package Comparators;


import Corpus.*;
import Features.StatusFeature;

import java.util.Comparator;
import java.util.List;

public class Distance extends ZScoreDistance implements Comparator<Document> {
    public Distance(Corpus corpus, Document queryDocument){
        super(corpus, queryDocument);
    }

    public int compare(Document document1, Document document2) {
        return (int) (distance(document1) - distance(document2));
    }

    private double distance(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.pow((getZScore(statusFeature, queryDocument) - getZScore(statusFeature, document)), 2);
        }

        return zScoreTotal / statusFeatures.size();
    }
}
