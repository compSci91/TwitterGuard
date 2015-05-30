package Comparators;

import Corpus.*;
import Features.StatusFeature;

public abstract class ZScoreDistance {
    Corpus corpus;
    Document queryDocument;

    public ZScoreDistance(Corpus corpus, Document queryDocument){
        this.corpus = corpus;
        this.queryDocument = queryDocument;
    }

    public double getZScore(StatusFeature statusFeature, Document document){
        return (document.getValueForFeature(statusFeature) - corpus.getMean(statusFeature)) / corpus.getStandardDeviation(statusFeature);
    }
}
