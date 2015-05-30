import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.io.*;


public class RandomEnglishStatusListener implements StatusListener {
    private int numberOfStatusesToSave;
    private int statusesSaved;
    private int fileNumber = 1;

    public RandomEnglishStatusListener(int numberOfStatusesToSave){
       this.numberOfStatusesToSave = numberOfStatusesToSave;
       this.statusesSaved = 0;
    }

    public void onStatus(Status status) {
        String statusText = status.getText();

        if (status.getLang().equals("en")) {
            System.out.println("Saving: " + statusText);
            saveTweet(statusText);
        }

        if(fileNumber >= numberOfStatusesToSave+1){

            System.exit(0);
        }

    }

    private void saveTweet(String statusText){

        try{
        FileOutputStream fileOut =
                new FileOutputStream("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/Normal_Tweets/status"+ fileNumber++ + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(statusText);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved.");
        }catch(IOException io){
            System.out.println("There was an IOException");
        }
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               /*Do nothing*/
    }

    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
    }

    public void onScrubGeo(long userId, long upToStatusId) {
            /* Do nothing */
    }

    public void onStallWarning(StallWarning warning) {
        System.out.println("Got stall warning:" + warning);
    }

    public void onException(Exception ex) {
        ex.printStackTrace();
    }
}

