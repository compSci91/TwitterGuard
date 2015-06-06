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
        return (int) (delta(document1) - delta(document2));
    }

    private double delta(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            System.out.println(zScoreDistance.getZScore(statusFeature, queryDocument));
            zScoreTotal += Math.abs((zScoreDistance.getZScore(statusFeature, queryDocument) - zScoreDistance.getZScore(statusFeature, document)));
        }


        return zScoreTotal / statusFeatures.size();
    }
}
