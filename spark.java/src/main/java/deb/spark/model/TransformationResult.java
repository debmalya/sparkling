/**
 * 
 */
package deb.spark.model;

import java.util.List;

/**
 * @author debmalyajash
 *
 */
public class TransformationResult {
	private int characterCount = 0;
	private List<String> bulletedPoints;
	private List<String> keySkills;
	/**
	 * @return the characterCount
	 */
	public int getCharacterCount() {
		return characterCount;
	}
	/**
	 * @param characterCount the characterCount to set
	 */
	public void setCharacterCount(int characterCount) {
		this.characterCount = characterCount;
	}
	/**
	 * @return the bulletedPoints
	 */
	public List<String> getBulletedPoints() {
		return bulletedPoints;
	}
	/**
	 * @param bulletedPoints the bulletedPoints to set
	 */
	public void setBulletedPoints(List<String> bulletedPoints) {
		this.bulletedPoints = bulletedPoints;
	}
	/**
	 * @return the keySkills
	 */
	public List<String> getKeySkills() {
		return keySkills;
	}
	/**
	 * @param keySkills the keySkills to set
	 */
	public void setKeySkills(List<String> keySkills) {
		this.keySkills = keySkills;
	}
	
	
}
