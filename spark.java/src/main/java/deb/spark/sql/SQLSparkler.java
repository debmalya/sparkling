/**
 * 
 */
package deb.spark.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import deb.spark.constants.Constants;
import deb.spark.model.Data;

/**
 * @author debmalyajash
 *
 */
public class SQLSparkler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			getData(args[0],Integer.parseInt(args[1]));
		} else {
			System.err.println("Please mention input file and most important column number");
		}

	}
	
	/**
	 * 
	 * @param csvLocation location of CSV file.
	 * @param determiningColumnIndex determining column index. 
	 * @return list data for each column wise distinct column value, determining column value, count.
	 */
	public static List<Data> getData(String csvLocation,int determiningColumnIndex) {
		List<Data> result = new ArrayList<Data>();
		
		JavaSparkContext javaSparkContext = getJavaSparkContext();
		SQLContext sqlContext = new org.apache.spark.sql.SQLContext(javaSparkContext);
		DataFrame dataFrame = sqlContext.load(csvLocation);
		System.out.println(dataFrame.count());
		return result;
	}

	/**
	 * To get Java Spark Context
	 * @return
	 */
	private static JavaSparkContext getJavaSparkContext() {
		SparkConf sparkConf = new SparkConf().setAppName(Constants.APP_NAME);
		sparkConf.setMaster(Constants.MASTER);

		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
		return ctx;
	}

}
