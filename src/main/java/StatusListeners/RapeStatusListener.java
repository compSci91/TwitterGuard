package StatusListeners;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RapeStatusListener implements StatusListener {
        private int fileNumber = 1;

        @Override
        public void onStatus(Status status) {
            if(statusIsSuspicious(status)) {
                System.out.println(status.getText());
                attemptSaveStatus(status);
            }
        }

    private void attemptSaveStatus(Status status) {
        try
        {
            saveTweet(status);
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    private void saveTweet(Status status) throws IOException {
        FileOutputStream fileOut =
                new FileOutputStream("/Users/joshuahowell/Desktop/Twitter_Guard/Corpus.Corpus/status"+ fileNumber++ + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(status);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved.");
    }

    private boolean statusIsSuspicious(Status status) {
        String englishLanguageCode = "en";
        return status.getLang().equals(englishLanguageCode) && containsRapeVariations(status.getText());
    }

    public boolean containsRapeVariations(String statusText){
            statusText = statusText.toLowerCase();
            List<String> statusWordsList = getWords(statusText);

            Set<String> triggerWords = new HashSet<String>(Arrays.asList("rape", "raped", "raping", "rapable", "rapeable", "rapist", "raper" ));

            for(String triggerWord : triggerWords){
                if(statusWordsList.contains(triggerWord))
                    return true;
            }

            return false;
        }

    private List<String> getWords(String status){
        List<String> words = new ArrayList<String>();
        Matcher m = Pattern.compile("[\\w'-]+").matcher(status);

        while (m.find()) {
            words.add(m.group());
        }

        return words;
    }
        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               /*Do nothing*/
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
            /* Do nothing */
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            System.out.println("Got stall warning:" + warning);
        }

        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
        }
}
