package Features;

import twitter4j.Status;

public class UseOfQuotations extends StatusFeature {
    public double returnValue(Status status) {
        if(status.getText().indexOf('"') == status.getText().lastIndexOf('"')){
            return 0;
        }else{
            return 1;
        }
    }
}
