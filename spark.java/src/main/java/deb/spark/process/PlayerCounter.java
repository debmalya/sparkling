/**
 * 
 */
package deb.spark.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;
import deb.spark.constants.Constants;

/**
 * @author debmalyajash
 *
 */
public class PlayerCounter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5520664804262297840L;

	/**
	 * To store keywords and their counts.
	 */
//	private List<Tuple2<String, Integer>> output = null;
	
	private Map<String,Integer> keywordMap = new HashMap<>();

	/**
	 * @return the keywordMap
	 */
	public Map<String, Integer> getKeywordMap() {
		if (keywordMap.isEmpty()) {
			keyWordoCounterByDirectory();
		}
		return keywordMap;
	}





	private Properties properties = new Properties();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	private void keyWordoCounterByDirectory() {
		// Load properties file
		InputStream inputStream = getClass().getResourceAsStream(
				"MUNLEI.properties");
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SparkConf sparkConf = new SparkConf().setAppName(Constants.APP_NAME);
		sparkConf.setMaster(Constants.MASTER);

		JavaSparkContext ctx = new JavaSparkContext(sparkConf);

		JavaRDD<String> lines = ctx.textFile("resource/EPL/*.txt", 1);

		JavaRDD<String> words = lines
				.flatMap(new FlatMapFunction<String, String>() {
					@Override
					public Iterable<String> call(String s) {
						return Arrays.asList(Constants.SPACE.split(s));
					}
				});

		JavaPairRDD<String, Integer> ones = words
				.mapToPair(new PairFunction<String, String, Integer>() {
					@Override
					public Tuple2<String, Integer> call(String s) {
						return new Tuple2<String, Integer>(s, 1);
					}
				});

		JavaPairRDD<String, Integer> counts = ones
				.reduceByKey(new Function2<Integer, Integer, Integer>() {
					@Override
					public Integer call(Integer i1, Integer i2) {
						return i1 + i2;
					}
				});

//		setOutput(counts.collect());

		List<Tuple2<String, Integer>> all = counts.collect();
		if (properties != null) {
			for (Tuple2<String, Integer> tuple : all) {
//				BUG : case sensitive
				if (properties.getProperty(tuple._1().toString()) != null)
					keywordMap.put(tuple._1().toString(),tuple._2());
			}
		}
		ctx.stop();
	}

	

	

	class TupleComparator implements Comparable<Tuple2<String, Integer>> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(Tuple2<String, Integer> o) {

			return 0;
		}

	}

}
