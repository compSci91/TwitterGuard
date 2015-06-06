package Comparators;

import Corpus.*;
import Features.StatusFeature;

public class ZScoreDistance {
    Corpus corpus;

    public ZScoreDistance(Corpus corpus){
        this.corpus = corpus;
    }

    public double getZScore(StatusFeature statusFeature, Document document){
       return (document.getValueForFeature(statusFeature) - corpus.getMean(statusFeature)) / corpus.getStandardDeviation(statusFeature);
    }
}
