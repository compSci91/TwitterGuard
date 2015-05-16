package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfUserMentionsFeature implements StatusFeature{
    Normalizer normalizer;

    public NumberOfUserMentionsFeature(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfUserMentionsFeature(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        return status.getUserMentionEntities().length;
    }
}
