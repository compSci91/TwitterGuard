import StatusListeners.*;
import StatusListeners.UserStreamListener;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.OAuthSupport;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Main {
	public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("7uQ8pqyCyIaqS47nApnppZHFB")
                .setOAuthConsumerSecret("MglBufHlQItfhgiwPNZWif1IHVuhs5DlGtwja1HOUpF7EMTk7L")
                .setOAuthAccessToken("74073469-Xis2oisWexPWgkUXnyhkG4siAG51SQ7ShlQyHysfO")
                .setOAuthAccessTokenSecret("h9s5KsprP9JSjpcLcdNcKBE8chvNp5lC733HaeR6C9Cmb");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener statusListener = new UserStreamListener();
        twitterStream.addListener(statusListener);
        twitterStream.user();
	}
}
