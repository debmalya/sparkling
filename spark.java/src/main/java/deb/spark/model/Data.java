/**
 * 
 */
package deb.spark.model;

/**
 * @author debmalyajash
 *
 */
public class Data {
	private String value;
	private long count;
	private String determinantColumnValue;
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
	/**
	 * @return the determinantColumnValue
	 */
	public String getDeterminantColumnValue() {
		return determinantColumnValue;
	}
	/**
	 * @param determinantColumnValue the determinantColumnValue to set
	 */
	public void setDeterminantColumnValue(String determinantColumnValue) {
		this.determinantColumnValue = determinantColumnValue;
	}
	
}
