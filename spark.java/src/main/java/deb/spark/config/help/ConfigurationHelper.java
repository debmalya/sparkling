/**
 * 
 */
package deb.spark.config.help;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import deb.spark.constants.Constants;

/**
 * @author debmalyajash
 *
 */
public class ConfigurationHelper {

	private static Properties twitterProperties = new Properties();

	private static class ConfigurationSingletonHolder {
		private final static ConfigurationHelper instance = new ConfigurationHelper();
	}

	private ConfigurationHelper() throws ExceptionInInitializerError {
		// Load properties file
		InputStream inputStream = getClass().getResourceAsStream(
				Constants.TWITTER_CONFIG);
		if (inputStream != null) {
			try {
				twitterProperties.load(inputStream);
			} catch (IOException e) {
				throw new ExceptionInInitializerError(e);
			}
		}

	}

	public static ConfigurationHelper getInstance() {
		try {
			return ConfigurationSingletonHolder.instance;
		} catch (ExceptionInInitializerError ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String getProperty(String propertyName) {
		return twitterProperties.getProperty(propertyName);
	}
}
