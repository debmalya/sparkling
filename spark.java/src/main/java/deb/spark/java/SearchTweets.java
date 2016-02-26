/**
 * 
 */
package deb.spark.java;

import java.util.List;

import deb.spark.config.help.ConfigurationHelper;
import deb.spark.constants.Constants;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author debmalyajash
 *
 */
public class SearchTweets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("java deb.spark.java.SearchTweets [query]");
			System.exit(-1);
		}

		ConfigurationBuilder cb = new ConfigurationBuilder();

		ConfigurationHelper configurationHelper = ConfigurationHelper
				.getInstance();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(
						configurationHelper
								.getProperty(Constants.OAUTH_CONSUMER_KEY))
				.setOAuthConsumerSecret(
						configurationHelper
								.getProperty(Constants.OAUTH_CONSUMER_SECRET))
				.setOAuthAccessToken(
						configurationHelper
								.getProperty(Constants.OAUTH_ACCESS_TOKEN))
				.setOAuthAccessTokenSecret(
						configurationHelper
								.getProperty(Constants.OAUTH_ACCESS_TOKNE_SECRET));
		
		
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		try {
			Query query = new Query(args[0]);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					System.out.println("@" + tweet.getUser().getScreenName()
							+ " - " + tweet.getText());
				}
			} while ((query = result.nextQuery()) != null);
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}

	}

}
