package Features;

import Features.Normalizers.NoNormalization;
import Features.Normalizers.Normalizer;
import twitter4j.Status;

public class NumberOfUserMentions extends StatusFeature{
    Normalizer normalizer;

    public NumberOfUserMentions(){
        this.normalizer = new NoNormalization();
    }

    public NumberOfUserMentions(Normalizer normalizer){
        this.normalizer = normalizer;
    }

    public double returnValue(Status status) {
        return status.getUserMentionEntities().length;
    }
}
