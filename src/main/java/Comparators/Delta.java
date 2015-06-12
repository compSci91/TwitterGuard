package Comparators;

import Features.StatusFeature;
import Corpus.*;

import java.util.Comparator;
import java.util.List;


public class Delta implements Comparator<Document>  {
    ZScoreDistance zScoreDistance;
    Document queryDocument;

    public Delta(Corpus corpus, Document queryDocument){
        zScoreDistance = new ZScoreDistance(corpus);
        this.queryDocument = queryDocument;
    }

    public int compare(Document document1, Document document2) {
        double actualDelta = delta(document1) - delta(document2);
        return (int) (actualDelta * ZScoreDistance.BILLSCONSTANT);
    }

    private double delta(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.abs((zScoreDistance.getZScore(statusFeature, queryDocument) - zScoreDistance.getZScore(statusFeature, document)));
        }

        return zScoreTotal / statusFeatures.size();
    }
}
