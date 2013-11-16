import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.GeoLocation;
import twitter4j.StatusUpdate;

import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.Calendar;

public class Tweetbot{
	static Calendar cal;
	static Calendar end;
	
	
	public static void main(String[] args){
		
		Thread pics = new Thread(new TweetPicture());
		Thread texts = new Thread(new TweetText());
		
		cal = Calendar.getInstance();
		end = Calendar.getInstance();
		end.set(Calendar.DAY_OF_MONTH, 16);
		end.set(Calendar.HOUR_OF_DAY, 12);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		
		pics.start();
		texts.start();
	}
	
	private static class TweetPicture implements Runnable{
		public void run(){
			cal = Calendar.getInstance();
			while(cal.before(end)){			
				try{
					Thread.sleep(10000);
					Twitter twitter = TwitterFactory.getSingleton();
					StatusUpdate statusUpdate = new StatusUpdate("2013 HACKATHON @ToppersPizza");
					statusUpdate.media(new File("xKE07.jpg"));
					statusUpdate.setLocation(new GeoLocation(42.83918333333, -88.74238333333));
					System.out.println("[TWEETBOT]Sending tweet with image");
					twitter.updateStatus(statusUpdate);	
				}
				catch(TwitterException e){
					e.printStackTrace();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}			
				cal = Calendar.getInstance();
			}
		}
	}
	
	private static class TweetText implements Runnable{
		public void run(){
			cal = Calendar.getInstance();
			while(cal.before(end)){	
				try{
					Thread.sleep(5000);
					Twitter twitter = TwitterFactory.getSingleton();
					StatusUpdate statusUpdate = new StatusUpdate("2013 HACKATHON @ToppersPizza");
					statusUpdate.setLocation(new GeoLocation(42.83918333333, -88.74238333333));
					System.out.println("[TWEETBOT]Sending tweet without image");
					twitter.updateStatus(statusUpdate);
				}
				catch(TwitterException e){
					e.printStackTrace();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}		
				cal = Calendar.getInstance();
			}
		}
	}
	
}


