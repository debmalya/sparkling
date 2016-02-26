/**
 * 
 */
package deb.spark.constants;

import java.util.regex.Pattern;

/**
 * @author debmalyajash
 *
 */
public class Constants {
	// Location of the Spark directory
	public static final String SPARK_HOME = "/Users/debmalyajash/Library/spark/spark-1.5.1";

	/**
	 * The master URL to connect to, such as "local" to run locally with one
	 * thread, "local[4]" to run locally with 4 cores, or "spark://master:7077"
	 * to run on a Spark stand alone cluster.
	 */
	public static final String MASTER = "local";

	/**
	 * Where I copied my files.
	 */
	public static final String PATH = "resource/*.txt";

	/**
	 * Application name
	 */
	public static final String APP_NAME = "Sparkling";

	/**
	 * 
	 */
	public static final Pattern SPACE = Pattern.compile(" ");
	
	public static final String CONFIG = "keyword.properties";
	
	public static final String TWITTER_CONFIG = "twitter.properties";
	
	/**
	 * Main keywords
	 */
	public static final String[] skills = new String[]{"Rails"};
	
	public static final String OAUTH_CONSUMER_KEY = "oauth.consumerKey";
	
	public static final String OAUTH_CONSUMER_SECRET = "oauth.consumerSecret";
	
	public static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
	
	public static final String OAUTH_ACCESS_TOKNE_SECRET = "oauth.accessTokenSecret";

}
