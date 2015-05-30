package Comparators;

import Features.StatusFeature;
import Corpus.*;

import java.util.Comparator;
import java.util.List;


public class Delta extends ZScoreDistance implements Comparator<Document>  {
    public Delta(Corpus corpus, Document queryDocument){
        super(corpus, queryDocument);
    }

    public int compare(Document document1, Document document2) {
        return (int) (delta(document1) - delta(document2));
    }

    private double delta(Document document){
        List<StatusFeature> statusFeatures = queryDocument.getStatusFeatures();
        double zScoreTotal = 0;

        for(StatusFeature statusFeature : statusFeatures){
            zScoreTotal += Math.abs((getZScore(statusFeature, queryDocument) - getZScore(statusFeature, document)));
        }

        return zScoreTotal / statusFeatures.size();
    }
}
