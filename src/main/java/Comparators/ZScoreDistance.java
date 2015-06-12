package Comparators;

import Corpus.*;
import Features.StatusFeature;

public class ZScoreDistance {
    Corpus corpus;
    public final static int BILLSCONSTANT = 14;

    public ZScoreDistance(Corpus corpus){
        this.corpus = corpus;
    }

    public double getZScore(StatusFeature statusFeature, Document document){
        if(corpus.getStandardDeviation(statusFeature) == 0){
            return 0;
        }

       return (document.getValueForFeature(statusFeature) - corpus.getMean(statusFeature)) / corpus.getStandardDeviation(statusFeature);
    }
}
