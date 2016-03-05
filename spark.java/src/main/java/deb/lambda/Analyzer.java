/**
 * 
 */
package deb.lambda;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author debmalyajash
 *
 */
public class Analyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 1) {
			getData(args[0], Integer.parseInt(args[1]));
		} else {
			System.err
					.println("Please mention input file and most important column number");
		}

	}

	/**
	 * 
	 * @param csvLocation
	 *            location of CSV file.
	 * @param determiningColumnIndex
	 *            determining column index.
	 * @return list data for each column wise distinct column value, determining
	 *         column value, count.
	 */
	private static void getData(String csvLocation, int determiningColumnIndex) {
		CSVReader reader = null;
		try {

			reader = new CSVReader(new FileReader(csvLocation));
			List<String[]> allRecords = reader.readAll();
			if (allRecords != null && allRecords.size() > 0) {
				if (determiningColumnIndex > -1
						&& determiningColumnIndex < allRecords.get(0).length) {
					Stream<String[]> positive = allRecords.parallelStream()
							.filter(record -> record[determiningColumnIndex]
									.equals("1"));
					Stream<String[]> negative = allRecords.parallelStream()
							.filter(record -> record[determiningColumnIndex]
									.equals("0"));
					Stream<String[]> neutral = allRecords.parallelStream()
							.filter(record -> !record[determiningColumnIndex]
									.equals("0") && !record[243].equals("1"));
					if (determiningColumnIndex > 0) {
						// Stream<String[]> selectedColumnValues =
						// positive.distinct().
					}
					System.out
							.println("Number of records :" + allRecords.size()
									+ " positive :" + positive.count()
									+ " negative : " + negative.count()
									+ " neutral :" + neutral.count());
				} else {
					System.err.println("Column index must be between 0 and " + (allRecords.get(0).length - 1));
				}
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
