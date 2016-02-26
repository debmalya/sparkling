/**
 * 
 */
package deb.twitter.helper;

import java.io.IOException;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;

/**
 * @author debmalyajash
 *
 */
public class TwitterStatusListener  implements StatusListener{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws TwitterException, IOException{
		
	}
	
	 public void onStatus(Status status) {
//         System.out.println(status.getUser().getName() + " : " + status.getText());
//         System.out.println(status.getUser().getName() + " : " + status.getText());
     }
     public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
     public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
     public void onException(Exception ex) {
         ex.printStackTrace();
     }
		@Override
		public void onScrubGeo(long longitude, long latitude) {			
			System.out.println("longitude :" + longitude +", latitude : " + latitude);
			
		}
		@Override
		public void onStallWarning(StallWarning arg0) {
			// TODO Auto-generated method stub
			
		}
}
