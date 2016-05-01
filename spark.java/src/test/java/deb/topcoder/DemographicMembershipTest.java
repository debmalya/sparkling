package deb.topcoder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import deb.topcoder.DemographicMembership;
import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author debmalyajash
 *
 */
public class DemographicMembershipTest extends TestCase {

	/**
	 * Test method for
	 * {@link DemographicMembership#predict(int, java.lang.String[], java.lang.String[])}
	 * .
	 */
	public final void testPredict() {
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(
					new FileReader(
							"/Users/debmalyajash/git/sparkling/spark.java/resource/demographic_membership_training.csv"));
			String eachLine = "";
			List<String> trainingData = new ArrayList<String>();
			while ((eachLine = reader.readLine()) != null) {
				trainingData.add(eachLine);
			}
			DemographicMembership memberShip = new DemographicMembership();
			String[] data = new String[trainingData.size()];
			trainingData.toArray(data);
			
			
			int[] result = memberShip.predict(2, data, data);
			int score = 0;
			for (int i = 0; i < result.length; i++) {
				if (data[i].endsWith("1") && result[i] == 1){
					score++;
				}else if (data[i].endsWith("0") && result[i] == 0){
					score++;
				}
			}
			System.out.println("Score = " + score);
		} catch (Throwable th) {
			th.printStackTrace();
			fail(th.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

// first score 6299
// second score 3251