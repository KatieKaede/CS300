//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Twitter Feed
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem

///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Calendar;
import java.util.Iterator;

/**
 * 
 * The class TwiteratorTester ensures that all the info from Tweet.java class
 * and User.java class is accessible and works as intended.
 * @author katiekrause
 *
 */
public class TwiteratorTester {
	
	/**
	 * testUser() method contains a variety of instances to 
	 * evaluate if User() class is working properly.
	 * @return true if everything works as intended, false otherwise
	 */
	public static boolean testUser() {
		User user1 = new User("Jane_Doe");
		if (!user1.getUsername().equals("Jane_Doe") || 
				user1.isVerified()) {
			return false;
		}
		
		// Test for exception if username is null
		try {
			User user2 = new User(null);
			return false;
		} catch (IllegalArgumentException e) {
			// exception caught
		}
		
		// Test for exception if username is blank
		try {
			User user3 = new User("");
			return false;
		} catch (IllegalArgumentException e) {
			// exception caught
		}
		
		// Test for exception if username has asterisk
		try {
			User user4 = new User("John_Doe*");
			return false;
		} catch (IllegalArgumentException e) {
			// exception caught
		}
		
		// Testing verify() and revokeVerification() work as intended
		user1.verify();
		if (!user1.isVerified()) {
			return false;
		}
		user1.revokeVerification();
		if (user1.isVerified()) {
			return false;
		}
		
		// Test toString() method on a verified user
		User user5 = new User("uwmadison");
		user5.verify();
		if (!user5.toString().equals("@uwmadison*")) {
			return false;
		}
		
		// Test toString() method on a non-verified user
		User user6 = new User("dril");
		if (!user6.toString().equals("@dril")) {
			return false;
		}
	return true;
	}
	
	/**
	 * testTweet() method contains a variety of instances 
	 * to evaluate if Tweet() class is working properly.
	 * @return true if everything works as intended, false otherwise
	 */
	public static boolean testTweet() {
		Calendar test = Calendar.getInstance();
		test.set(2012, Calendar.MAY, 22, 14, 46, 03);
		Tweet.setCalendar(test);
		
		User drilUser = new User("dril");
		Tweet tweet = new Tweet(drilUser, "Hello! This is my "
				+ "first tweet.");
		
		//Test the getText() method
		if (!tweet.getText().equals("Hello! This is my first tweet.")) {
			return false;
		}
		
		// Test tweet constructor when user and text is null
		try {
			Tweet nullTweet = new Tweet(null, null);
			return false;
		} catch (NullPointerException e) {
			// exception caught
		}
		
		// Test when character limit is exceeded
		User extraUser = new User("i_tweet_too_much");
		String longText = "";
        for (int i = 0; i < 282; i++) {
            longText += "0";
        }
        
        try {
        	Tweet longTweet = new Tweet(extraUser, longText);
        	return false;
        } catch (IllegalArgumentException e) {
        	// exception caught
        }
        
        // Tests if getTotalEngagement() works as intended
        User engageUser = new User("engagement_check");
        Tweet engageTweet = new Tweet(engageUser, "How's my engagement?");
        engageTweet.like();
        engageTweet.retweet();
        if (engageTweet.getTotalEngagement() != 2) {
        	return false;
        }
        
        // Tests if isUserVerified() works as intended
        User verifiedUser = new User("im_verified");
        verifiedUser.verify();
        Tweet verifiedTweet = new Tweet(verifiedUser, "I'm verified hehe.");
        if (!verifiedTweet.isUserVerified()) {
        	return false;
        }
        
        // Tests if getLikesRatio() works as intended
        User noRatioUser = new User("no_ratio_user");
        User ratioUser = new User("ratio_user");
        Tweet noRatioTweet = new Tweet(noRatioUser, "I have no ratio ):");
        Tweet ratioTweet = new Tweet(ratioUser, "Check my ratio!");
        ratioTweet.like();
        ratioTweet.like();
        ratioTweet.retweet();
        
        if (noRatioTweet.getLikesRatio() != -1) {
        	return false;
        }
        
        if (ratioTweet.getLikesRatio() != 2.0 / 3.0) {
        	return false;
        }
        
        // Tests if equals() method works as intended
        Calendar timestamp = Calendar.getInstance();
        timestamp.set(2022, Calendar.APRIL, 12, 12, 1, 6);
        Tweet.setCalendar(timestamp);
        
        User firstUser = new User("first_user");
        User firstComparisonUser = new User("first_user");
        User secondUser = new User("second_user");
        User thirdUser = new User("third_user");
        
        Tweet firstTweet = new Tweet(firstUser, "Hey everyone!");
        firstTweet.like();
        firstTweet.retweet();
        Tweet firstCompare = new Tweet(firstComparisonUser, "Hey everyone!");
        Tweet secondTweet = new Tweet(secondUser, "Hey everyone!");
        Tweet thirdTweet = new Tweet(thirdUser, "Hey everyone! What's up?");
        Tweet fourthTweet = new Tweet(thirdUser, "It's been a long day.");
        
        // Tests same user and text
        if (!firstTweet.equals(firstCompare)) {
        	return false;
        }
        
        // Tests same text, different user
        if (firstTweet.equals(secondTweet)) {
        	return false;
        }
        
        // Tests same user, different text
        if (thirdTweet.equals(fourthTweet)) {
        	return false;
        }
        
        // Tests toString() method
        String firstTweetString = "tweet from @first_user at Tue Apr 12 12:01:06 "
        		+ "CDT 2022:"
        		+ "\n-- Hey everyone!\n"
        		+ "-- likes: 1\n"
        		+ "-- retweets: 1";
        
        if (!firstTweet.toString().equals(firstTweetString)) {
        	return false;
        }
        return true;
	}
	
	/**
	 * Contains a variety of test cases to ensure TweetNode() 
	 * works as intended
	 * @return true if all test cases pass, false otherwise
	 */
	public static boolean testNode() {
		Calendar timestamp = Calendar.getInstance();
        timestamp.set(2022, Calendar.JULY, 23, 10, 11, 10);
        Tweet.setCalendar(timestamp);
        
        User firstUser = new User("first_user");
        User secondUser = new User("second_user");
        User thirdUser = new User("third_user");
        
        Tweet firstTweet = new Tweet(firstUser, "I'm the first user!");
        Tweet secondTweet = new Tweet(secondUser, "I'm the second user!");
        Tweet thirdTweet = new Tweet(thirdUser, "I'm the third user!");
        
        TweetNode firstNode = new TweetNode(firstTweet);
        TweetNode secondNode = new TweetNode(secondTweet);
        TweetNode thirdNode = new TweetNode(firstTweet, secondNode);
        firstNode.setNext(secondNode);
        
        // Ensures that the first node text is the same as the first tweet
        if (!firstNode.getTweet().equals(firstTweet)) {
        	return false;
        }
        
        // Ensures that the second node text is the same as the second tweet
        if (!secondNode.getTweet().equals(secondTweet)) {
        	return false;
        }
        
        // Checks that the successor node of the first is the second node
        if (firstNode.getNext() != secondNode) {
        	return false;
        }
        
        // Checks that the second node has no successor
        if (secondNode.getNext() != null) {
        	return false;
        }
        // Test the constructor with two parameters
        if (thirdNode.getNext() != secondNode) {
            return false;
        }
        
        return true;
	}
	
	/**
	 * This method creates and ensures that an empty twitter feed is created
	 * With a variety of test cases, this method ensures that addFirst() and
	 * addLast() work as intended
	 * @return true if all test cases pass, false otherwise
	 */
	public static boolean testAddTweet() {
		TwitterFeed blankFeed = new TwitterFeed();
		User firstUser = new User("first_user");
		User secondUser = new User("second_user");
		Tweet firstTweet = new Tweet(firstUser, "I'm the first user!");
		Tweet secondTweet = new Tweet(secondUser, 
				"I'm the second user!");
		
		// Check that created feed is empty
		if (!blankFeed.isEmpty() || blankFeed.size() != 0) {
			return false;
		}
		
		// Checks if addFirst() adds a node to the head
		blankFeed.addFirst(firstTweet);
		
		// Checks if the feed has size 1 and is not empty anymore
		if (blankFeed.isEmpty() || blankFeed.size() != 1) {
			return false;
		}
		
		// Checks that the feed contains the just added tweet
		if (!blankFeed.contains(firstTweet)) {
			return false;
		}
		
		// Add another node
		blankFeed.addFirst(secondTweet);
		
		// Checks that get(0) returns the matching tweet at that index
		if (!blankFeed.get(0).equals(secondTweet)) {
			return false;
		}
		
		// Checks if getHead() returns the tweet in the first spot
		if (!blankFeed.getHead().equals(secondTweet)) {
			return false;
		}
		
		// Checks if getTail() returns the tweet in the last spot
		if (!blankFeed.getTail().equals(firstTweet)) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method creates a twitter feed and checks that add works as
	 * intended
	 * @return true if the variety of test cases are correct, false otherwise
	 */
	public static boolean testInsertTweet() {
		TwitterFeed blankFeed = new TwitterFeed();
		
		User firstUser = new User("first_user");
        User secondUser = new User("second_user");
        User thirdUser = new User("third_user");
        
        Tweet firstTweet = new Tweet(firstUser, "I'm first");
        Tweet secondTweet = new Tweet(secondUser, "I'm second");
        Tweet thirdTweet = new Tweet(thirdUser, "I'm third");
		
		blankFeed.add(0,  firstTweet);
		blankFeed.add(1, secondTweet);
		blankFeed.add(2, thirdTweet);
		
		// Checks that the size is correct
		if (blankFeed.size() != 3) {
			return false;
		}
		
		// Checks that get() returns the correct tweet at that index
		if (!blankFeed.get(0).equals(firstTweet)) {
			return false;
		}
		
		if (!blankFeed.get(1).equals(secondTweet)) {
			return false;
		}
		
		if (!blankFeed.get(2).equals(thirdTweet)) {
			return false;
		}
		
		// Checks that getHead() and getTail() returns the correct tweets
		if (!blankFeed.getHead().equals(firstTweet)) {
			return false;
		}
		
		if (!blankFeed.getTail().equals(thirdTweet)) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method creates a twitter feed with at least 5 objects, it checks
	 * that getTail() gets updated when removing the last tweet, and that
	 * getHead() gets updated when removing the first tweet
	 * It also checks that if you remove a tweet in one of the middle nodes,
	 * get() returns the new value we expect
	 * @return true if passes all test cases, false otherwise
	 */
	public static boolean testDeleteTweet() {
		TwitterFeed blankFeed = new TwitterFeed();
		User firstUser = new User("first_user");
        User secondUser = new User("second_user");
        User thirdUser = new User("third_user");
        User fourthUser = new User("fourth_user");
        User fifthUser = new User("fifth_user");
        
		Tweet firstTweet = new Tweet(firstUser, "I'm first");
        Tweet secondTweet = new Tweet(secondUser, "I'm second");
        Tweet thirdTweet = new Tweet(thirdUser, "I'm third");
        Tweet fourthTweet = new Tweet(fourthUser, "I'm fourth");
        Tweet fifthTweet = new Tweet(fifthUser, "I'm fifth");
        
        blankFeed.add(0, firstTweet);
		blankFeed.add(1, secondTweet);
		blankFeed.add(2, thirdTweet);
		blankFeed.add(3, fourthTweet);
		blankFeed.add(4, fifthTweet);
		
		// Remove the last tweet and check if getTail() has been updated
		Tweet removeThisTweet = blankFeed.delete(4);
		if (!removeThisTweet.equals(fifthTweet)) {
			return false;
		}
		
		if (!blankFeed.getTail().equals(fourthTweet)) {
			return false;
		}
		
		// Remove the first tweet and see if getHead() has been updated
		Tweet removeHead = blankFeed.delete(0);
		if (!removeHead.equals(firstTweet)) {
			return false;
		}
		
		if (!blankFeed.getHead().equals(secondTweet)) {
			return false;
		}
		
		// Remove a tweet in a middle node and checking if get() returns
		// the expected tweet
		// The nodes should no longer have firstTweet or fifthTweet
		Tweet removeMiddle = blankFeed.delete(1);
		if (!removeMiddle.equals(thirdTweet)) {
			return false;
		}
		return true;
	}
		
		/**
		 * Checks to make sure ChronoTwiterator class works as intended
		 * @return true if all test cases pass, false otherwise
		 */
		public static boolean testChronoTwiterator() {
			// Make a TwitterFeed object and add at least 
			// 3 tweets to it
			Calendar timestamp = Calendar.getInstance();
	        timestamp.set(2022, Calendar.JANUARY, 13, 15, 10, 10);
	        Tweet.setCalendar(timestamp);
	        
	        // Create a new feed
	        TwitterFeed blankFeed = new TwitterFeed();
	        User firstUser = new User("first_user");
	        User secondUser = new User("second_user");
	        User thirdUser = new User("third_user");
	        
	        Tweet firstTweet = new Tweet(firstUser, "I'm first");
	        Tweet secondTweet = new Tweet(secondUser, "I'm second");
	        Tweet thirdTweet = new Tweet(thirdUser, "I'm third");
	        
	        blankFeed.add(0, firstTweet);
			blankFeed.add(1, secondTweet);
			blankFeed.add(2, thirdTweet);
			
			// Make the mode chronological using ChronoTwiterator
			blankFeed.setMode(TimelineMode.CHRONOLOGICAL);
			
			Iterator<Tweet> chronoTwiterator = blankFeed.iterator();
			for (int i = 0; i < blankFeed.size(); i++) {
				Tweet tweet = blankFeed.get(i);
				Tweet successorTweet = chronoTwiterator.next();
				if (!tweet.equals(successorTweet)) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * Checks to make sure VerifiedTwiterator class works as intended
		 * @return true if all test cases pass, false otherwise
		 */
		public static boolean testVerifiedTwiterator() {
			Calendar timestamp = Calendar.getInstance();
	        timestamp.set(2022, Calendar.JANUARY, 13, 15, 10, 10);
	        Tweet.setCalendar(timestamp);
	        
	        // Create a new feed
	        TwitterFeed blankFeed = new TwitterFeed();
	        
	        User firstUser = new User("first_user");
	        User secondUser = new User("second_user");
	        User verifiedUser = new User("verified_user");
	        verifiedUser.verify();
	        
	        Tweet firstTweet = new Tweet(firstUser, "I'm first");
	        Tweet secondTweet = new Tweet(secondUser, "I'm second");
	        Tweet verifiedTweet = new Tweet(verifiedUser, "I'm verified");
	        
	        blankFeed.add(0, firstTweet);
			blankFeed.add(1, secondTweet);
			blankFeed.add(2, verifiedTweet);
			
			// Make the mode show Verified only
			blankFeed.setMode(TimelineMode.VERIFIED_ONLY);
			
			// Checks that VerifiedTwiterator is in effect
			Iterator<Tweet> twiterator = blankFeed.iterator();
	        for (int i = 0; i < blankFeed.size(); i++) {
				Tweet tweet = blankFeed.get(i);
				
				if (tweet.isUserVerified()) {
					Tweet successorTweet = twiterator.next();
					while(!successorTweet.isUserVerified()) {
						successorTweet = 
								twiterator.next();
					}
				
				if (!tweet.equals(successorTweet)) {
					return false;
				}
	        }
	        }
	        return true;
		}
		
		/**
		 * Checks to make sure RatioTwiterator class works as intended
		 * @return true if all test cases pass, false otherwise
		 */
		public static boolean testRatioTwiterator() {
			Calendar timestamp = Calendar.getInstance();
	        timestamp.set(2022, Calendar.JANUARY, 13, 15, 10, 10);
	        Tweet.setCalendar(timestamp);
	        
	        // Create a new feed
	        TwitterFeed blankFeed = new TwitterFeed();
	        User firstUser = new User("first_user");
	        User secondUser = new User("second_user");
	        User thirdUser = new User("third_user");
	        
	        Tweet firstTweet = new Tweet(firstUser, "I'm first");
	        Tweet secondTweet = new Tweet(secondUser, "I'm second");
	        Tweet thirdTweet = new Tweet(thirdUser, "I'm third");
	        
	        blankFeed.add(0, firstTweet);
			blankFeed.add(1, secondTweet);
			blankFeed.add(2, thirdTweet);
			
			// add likes
			firstTweet.like();
			
			secondTweet.like();
			secondTweet.like();
			
			thirdTweet.like();
			thirdTweet.like();
			thirdTweet.like();
			
			// add retweets
			secondTweet.retweet();
			thirdTweet.retweet();
			
			// Make the mode to Ratio
			blankFeed.setMode(TimelineMode.LIKE_RATIO);
			Iterator<Tweet> ratioTwiterator = blankFeed.iterator();
			double thresHold = 0.5;
			
			while (ratioTwiterator.hasNext()) {
				Tweet successorTweet = ratioTwiterator.next();
				if (successorTweet.getLikesRatio() < thresHold) {
					return false;
				}
			}
			return true;
		}
	
	/**
	 * Twiterator tester's main method	
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("testUser is " + testUser() + "!");
		System.out.println("testTweet is " + testTweet() + "!");
		System.out.println("testNode is " + testNode() + "!");
		System.out.println("testAddTweet is " + testAddTweet() + "!");
		System.out.println("testInsertTweet is " + testInsertTweet() 
		+ "!");
		System.out.println("testDeleteTweet is " + testDeleteTweet() 
		+ "!");
		System.out.println("testChronoTwiterator is " 
		+ testChronoTwiterator() + "!");
		System.out.println("testVerifiedTwitter is " 
		+ testVerifiedTwiterator() + "!");
		System.out.println("testRatioTwiterator is " 
		+ testRatioTwiterator() + "!"); 
	}
}
