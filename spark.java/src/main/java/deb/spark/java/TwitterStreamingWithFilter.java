/**
 * 
 */
package deb.spark.java;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
import deb.spark.config.help.ConfigurationHelper;
import deb.spark.constants.Constants;
import deb.twitter.helper.TwitterStatusListener;

/**
 * @author debmalyajash
 *
 */
public class TwitterStreamingWithFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TwitterStatusListener listener = new TwitterStatusListener();

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

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
				.getInstance();
		twitterStream.addListener(listener);
		
		//
//		FilterQuery query = new FilterQuery();
//		query.track(new String[]{"Get Java Jobs","@GetJavaJobs"});
		twitterStream.filter("Get Java Jobs","@GetJavaJobs","Arsenal","@Arsenal");
//		twitterStream.filter("Topcoder","@Topcoder");
		
		// sample() method internally creates a thread which manipulates
		// TwitterStream and calls these adequate listener methods continuously.
		twitterStream.sample();

	}

}
