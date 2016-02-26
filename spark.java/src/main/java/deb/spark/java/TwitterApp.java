/**
 * 
 */
package deb.spark.java;

import java.util.List;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import twitter4j.HttpClientConfiguration;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import deb.spark.config.help.ConfigurationHelper;
import deb.spark.constants.Constants;

/**
 * @author debmalyajash
 *
 */
public class TwitterApp {

	/**
	 * http://zdatainc.com/2014/08/real-time-streaming-apache-spark-streaming/
	 * https://spark.apache.org/docs/0.8.1/streaming-programming-guide.html
	 * 
	 * @param <StreamingContext>
	 * @param args
	 */
	public static void main(String[] args) {
		SparkConf conf = new SparkConf()
				.setAppName("Twitter Sentiment Analysis");

		if (args.length > 0)
			conf.setMaster(args[0]);
		else
			conf.setMaster("local[2]");

		JavaSparkContext sparkContext = new JavaSparkContext(conf);

		// A StreamingContext is the main entry point for Spark Streaming
		// functionality. Besides the basic information (such as, cluster URL
		// and job name) to internally create a SparkContext, it provides
		// methods used to create DStream from various input sources.
		Duration batchDuration = new Duration(5000);

		JavaStreamingContext ssc = new JavaStreamingContext(sparkContext,
				batchDuration);
		// JavaStreamingContext ssc = new JavaStreamingContext(
		// ssc.socketStream("http://java.sun.com", 80, converter, storageLevel);
		 JavaDStream<String> receiverStream = ssc.textFileStream(Constants.SPARK_HOME);

		// What is DStream ?
		// A Discretized Stream (DStream), the basic abstraction in Spark
		// Streaming, is a continuous sequence of RDDs (of the same type)
		// representing a continuous stream of data.
						 
		 ConfigurationBuilder cb = new ConfigurationBuilder();
		 
		 ConfigurationHelper configurationHelper = ConfigurationHelper.getInstance();		 
		 cb.setDebugEnabled(true)
		   .setOAuthConsumerKey(configurationHelper.getProperty(Constants.OAUTH_CONSUMER_KEY))
		   .setOAuthConsumerSecret(configurationHelper.getProperty(Constants.OAUTH_CONSUMER_SECRET))
		   .setOAuthAccessToken(configurationHelper.getProperty(Constants.OAUTH_ACCESS_TOKEN))
		   .setOAuthAccessTokenSecret(configurationHelper.getProperty(Constants.OAUTH_ACCESS_TOKNE_SECRET));
		 
		 
		 TwitterFactory tf = new TwitterFactory(cb.build());
		 Twitter twitter = tf.getInstance();
		 

		
		 try {
		    List<Status> statuses = twitter.getHomeTimeline();
		    System.out.println("Showing home timeline.");
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ":" +
		                           status.getText());
		    }
		 } catch(TwitterException twe) {
			 twe.printStackTrace();
		 }

	}

}
