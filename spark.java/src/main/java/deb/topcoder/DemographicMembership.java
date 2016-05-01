package deb.topcoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/**
 * @author debmalyajash
 *
 */
public class DemographicMembership {


	/**
	 * There are total 243 columns.
	 */
	private static final int TOTAL_NUMBER_OF_COLUMNS = 243;
	
	private static int successCount = 0;
	
	private static int failureCount = 0;

	/**
	 * For each column there will be map. Each map, will have distinct value for
	 * the column as key. Value will be an integer array. First element of the
	 * array, will be count for value "1". Second element of the array, will be
	 * count for value "0".
	 */
	private static List<Map<String, int[]>> dataMap = new ArrayList<Map<String, int[]>>(
			TOTAL_NUMBER_OF_COLUMNS);

	/**
	 * 
	 * @param testType
	 *            0, 1, or 2, to indicate Example, Provisional, or System test,
	 *            respectively. The full data set is divided randomly into 40%
	 *            for example tests, 20% for provisional tests, and 40% for
	 *            system tests.
	 * @param trainingData
	 *            In the string[] trainingData, each string contains a single
	 *            record, and has 244 tokens, comma-separated.
	 * @param testingData
	 *            The format of testingData is the same as the trainingData,
	 *            except for the absence of the last column, which is the
	 *            variable to be predicted.
	 * @return
	 */
	public int[] predict(int testType, String[] trainingData,
			String[] testingData) {
		init();

		analyzeData(trainingData);
		return predictData(testingData);
	}

	/**
	 * 
	 */
	private void init() {
		successCount = 0;
		failureCount = 0;
		
	}

	/**
	 * @param testingData
	 * @return
	 */
	private int[] predictData(String[] testingData) {
		int[] result = new int[testingData.length];
		
		for (int i = 0; i < testingData.length; i++) {
			String[] row = testingData[i].split(",");
			int voteFor = 0;
			int voteAgainst = 0;
			for (int j = 1; j < TOTAL_NUMBER_OF_COLUMNS - 1; j++) {
				
				Map<String, int[]> map = dataMap.get(j - 1);
				if (map != null) {
					int[] votes = map.get(row[j]);
					if (votes != null ){
						voteFor += votes[0];
						voteAgainst += votes[1];
					}
				}
			}
			
//			if ((double)voteFor/successCount > (double)voteAgainst/failureCount) {
			if (voteFor > voteAgainst) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}
		
		return result;
	}

	/**
	 * @param trainingData
	 */
	private void analyzeData(String[] trainingData) {
		for (int i = 0; i < trainingData.length; i++) {
			// 0th column is CONSUMER_ID, ignoring that one.
			// Starting from AGE to INTEREST_GREEN
			String[] row = trainingData[i].split(",");
			for (int j = 1; j < TOTAL_NUMBER_OF_COLUMNS - 1; j++) {
				if (dataMap.size()  < j) {
					dataMap.add(new HashMap<String,int[]>());
				}
				Map<String, int[]> map = dataMap.get(j - 1);
				if (map == null) {
					map = new HashMap<String,int[]>();
				}
				int[] values = map.get(row[j]);
				if (values == null) {
					values = new int[2];
				}
				if (row[TOTAL_NUMBER_OF_COLUMNS].equals("1")){
					values[0]++;
					successCount++;
				} else {
					values[1]++;
					failureCount++;
				}
				map.put(row[j],values);
				dataMap.set(j - 1, map);
			}
		}
		
	}
}
