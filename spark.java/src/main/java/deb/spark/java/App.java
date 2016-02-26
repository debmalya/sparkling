package deb.spark.java;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;
import deb.spark.constants.Constants;
import deb.spark.model.ActionResult;

/**
 * Hello world!
 *
 */
public class App {

	



	/**
	 * http://spark.apache.org/docs/latest/programming-guide.html#overview
	 * @param args
	 */
	public static void main(String[] args) {

		// To create a SparkContext you first need to build a SparkConf object
		// that contains information about your application.
		SparkConf conf = new SparkConf().setAppName(Constants.APP_NAME).setMaster(Constants.MASTER);

		// create a JavaSparkContext object, which tells Spark how to access a
		// cluster.
		try (JavaSparkContext sc = new JavaSparkContext(conf)) {

			// Text file RDDs can be created using SparkContext’s textFile
			// method.
			// This method takes an URI for the file (either a local path on the
			// machine, or a hdfs://, s3n://, etc URI) and reads it as a
			// collection
			// of lines. Here is an example invocation:

			// Text file RDDs can be created using SparkContext’s textFile
			// method.
			// The textFile method also takes an optional second argument for
			// controlling the number of partitions of the file. By default,
			// Spark creates one partition for each block of the file (blocks
			// being 64MB by default in HDFS), but you can also ask for a higher
			// number of partitions by passing a larger value. Note that you
			// cannot have fewer partitions than blocks.
			JavaRDD<String> distFile = sc.textFile(Constants.PATH);

			doAction(distFile);

			doTransformation(distFile);

			printFooter();
		}
	}

	/**
	 * Once created, distFile can be acted on by data set operations. actions,
	 * which return a value to the driver program after running a computation on
	 * the data set.
	 * 
	 * @param distFile
	 */
	private static void doAction(JavaRDD<String> distFile) {
		ActionResult result = new ActionResult();

		System.out.println("XXXXXXXX Action XXXXXXXX");
		result.setLineCount(distFile.count());
		
		result.setFirstLine(distFile.first());
		result.setLastLine(distFile.take((int)distFile.count()));
		
		
		

		result.print();
		System.out.println("XXXXXXXX  Action Cut XXXXXXXX");
	}

	/**
	 * transformations, which create a new dataset from an existing one.
	 * 
	 * @param distFile
	 */
	private static void doTransformation(JavaRDD<String> distFile) {
		// Calculate number of characters.
		JavaRDD<Integer> lineLengths = distFile.map(s -> s.length());
		lineLengths.reduce((a, b) -> a + b);
		
		// Map line occurrences. Not so useful
		JavaPairRDD<String, Integer> pairs = distFile.mapToPair(s -> new Tuple2<String,Integer>(s, 1));
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey((a, b) -> a + b);
		
		// Rule - > Responsibilities start with "." 
		System.out.println("Now Bullet points processing starts");
		List<String> mainPoints = distFile.distinct().filter(s->s.startsWith("· ")).collect();		
		mainPoints.forEach(s->System.out.println(s));
		
		// Rule - > Key skill are separated by  "," 
		System.out.println("Now Key Skill Processing starts");
		List<String> keySkills = distFile.distinct().filter( s->s.contains(",") && !s.startsWith("· ") ).collect();		
		keySkills.forEach(s->System.out.println(s));
		
		
		
		
	}

	/**
	 * 
	 */
	private static void printFooter() {
		System.out.println("================");
		System.out.println("Sparkling World!");
		System.out.println("================");
	}

}
