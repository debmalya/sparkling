/**
 * 
 */
package deb.spark.model;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author debmalyajash
 *
 */
public class ActionResult {
	/**
	 * Number of lines.
	 */
	private long lineCount;

	private String firstLine;
	private List<String> lastLine;

	/**
	 * @return the lineCount
	 */
	public long getLineCount() {
		return lineCount;
	}

	/**
	 * @param lineCount
	 *            the lineCount to set
	 */
	public void setLineCount(long lineCount) {
		this.lineCount = lineCount;
	}

	/**
	 * @return the firstLine
	 */
	public String getFirstLine() {
		return firstLine;
	}

	/**
	 * @param firstLine
	 *            the firstLine to set
	 */
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	/**
	 * @return the lastLine
	 */
	public List<String> getLastLine() {
		return lastLine;
	}

	/**
	 * @param list
	 *            the lastLine to set
	 */
	public void setLastLine(List<String> list) {
		this.lastLine = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder aboutMe = new StringBuilder();
		aboutMe.append("ActionResult [lineCount=" + lineCount + "]");

		return aboutMe.toString();
	}

	/**
	 * 
	 */
	public void print() {
		System.out.println(this);
		lastLine.forEach(System.out::println);
		processWords();

	}

	public void processWords() {
		if (lastLine != null) {
			Stream<String> words = lastLine.stream().distinct();
			words.forEach(System.out::println);
		}
	}

}
