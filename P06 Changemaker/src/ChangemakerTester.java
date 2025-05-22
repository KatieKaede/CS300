//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Katie Krause
// Email:    klkrause5@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Eugene Park
// Partner Email:   empark@wisc.edu
// Partner Lecturer's Name: Hobbes Legault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Eugene Park, my programming partner, helped me implement
//					every method and tester
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

/**
 * This class contains a variety of tester methods to determine if the 
 * Changemaker class's methods are functioning properly with 
 * real mathematical scenarios. Each Changemaker method has two test methods, 
 * one to determine the validity of the method's base case, 
 * and the other to verify the recursive call works as well
 * 
 * @author Eugene Park and Katie Krause
 */
public class ChangemakerTester {
	
	/**
	 * This tester method ensures the validity of Changemaker's count() 
	 * BASE call With three scenarios, these tests verify the 
	 * different outputs when a user uses count and makes 
	 * non-logical withdrawals
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testCountBase() {
	
		// (1) Tests what count() outputs if given value is negative
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemaining = {1, 2, 3 ,4};
			int value = -1;
		
			int optionsActual = Changemaker.count(denominations, 
					coinsRemaining, value);
			int optionsExpected = 0;
		
			if (optionsActual != optionsExpected) return false;
		}

		// (2) Tests what count() outputs if value is positive, 
		// but change is not possible
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemaining = {0, 0, 0 ,1};
			int value = 30;
			
			int optionsActual = Changemaker.count(denominations, 
					coinsRemaining, value);
			int optionsExpected = 0;
			
			if (optionsActual != optionsExpected) return false;
		}
		
		// (3) Tests what count() outputs when value = 0
		{
			int[] denominations = {1, 10, 25};
			int[] coinsRemaining = {1, 2, 3};
			int value = 0;
			
			int optionsActual = Changemaker.count(denominations, 
					coinsRemaining, value);
			int optionsExpected = 1;
			
			if (optionsActual != optionsExpected) return false;
		}
		
	return true;
	
	}
	
	/**
	 * This tester method ensures the validity of Changemaker's count() 
	 * RECURSIVE call. With three scenarios, these tests run 
	 * complex and specific instances to verify that count() functions 
	 * properly
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testCountRecursive() {
		
		// (1) Tests if count() outputs correct the number of combos 
		// when three different coins can be used to make change
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemianing = {0, 1, 1, 2};
			int value = 30;
			
			int optionsActual = Changemaker.count(denominations, 
					coinsRemianing, value);
			int optionsExpected = 2;
			
			if (optionsActual != optionsExpected) return false;
		}
		
		// (2) Tests if count() outputs correct result when there 
		// are two ways to make change
		{
			int[] denominations = {1, 10, 15, 24};
			int[] coinsRemaining = {1, 1, 1, 1};
			int value = 25;
			
			int optionsActual = Changemaker.count(denominations, 
					coinsRemaining, value);
			int optionsExpected = 4;
			
			if (optionsActual != optionsExpected) return false;
		}
		
		// (3) Tests if count() returns the result that uses up 
		// the least amount of coins rather than 
		// simply choosing the biggest coin
		{
			int[] denominations = {1, 3, 6, 7};
			int[] coinsRemaining = {2, 1, 1, 1};
			int value = 9;
			
			int optionsActual = Changemaker.count(denominations, 
					coinsRemaining, value);
			int optionsExpected = 5;
			
			if (optionsActual != optionsExpected) return false;
		}
		
		return true;
	}
	
	/**
	 * This tester method ensures the validity of Changemaker's 
	 * minCoins() BASE call With three scenarios, these tests verify 
	 * the different outputs when a user uses count and makes 
	 * non-logical withdrawals
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testMinCoinsBase() {
		
		// (1) Tests minCoins() to see if it returns -1 when 
		// value is negative
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemianing = {0, 1, 1, 2};
			int value = -3;
			
			int minActual = Changemaker.minCoins(denominations, 
					coinsRemianing, value);
			int minExpected = -1;
			
			if (minActual != minExpected) return false;
		}
		
		// (2) Tests minCoins() to see if it returns -1 when 
		// value is positive but there is no way to make change
		{
			int[] denominations = {1, 10, 15, 24};
			int[] coinsRemaining = {1, 1, 1, 1};
			int value = 8;
			
			int minActual = Changemaker.minCoins(denominations, 
					coinsRemaining, value);
			int minExpected = -1;
			
			if (minActual != minExpected) return false;
		}	
		
		// (3) Tests minCoins() to see if it returns 0 when value is 0
		{ 
			int[] denominations = {1, 3, 6, 7};
			int[] coinsRemaining = {2, 1, 1, 1};
			int value = 0;
			
			int minActual = Changemaker.minCoins(denominations, 
					coinsRemaining, value);
			int minExpected = 0;
			
			if (minActual != minExpected) return false;
		}
		
		return true;
		
	}
	
	/**
	 * This tester method ensures the validity of Changemaker's 
	 * minCoins() RECURSIVE call. With three scenarios, 
	 * these tests run complex and specific instances to verify
	 * that minCoins handles callback functions properly
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testMinCoinsRecursive() {
		
		// (1) Makes sure the correct # of minCoins is returned when 
		// three different coins are required
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemaining = {5, 1, 2, 0};
			int value = 30;
			
			int minActual = Changemaker.minCoins(denominations, 
					coinsRemaining, value);
			int minExpected = 8;
			
			if (minActual != minExpected) return false;
		}
		
		// (2) Tests to see if the correct # of minCoins is returned
		// when there's two ways to make the value 
		// that are both equally optimal
		{
			int[] denominations = {1, 10, 15, 24};
			int[] coinsRemaining = {1, 1, 1, 1};
			int value = 25;
			
			int minActual = Changemaker.minCoins(denominations, 
					coinsRemaining, value);
			int minExpected = 2;
			
			if (minActual != minExpected) return false;
		}
		
		//(3) Tests to see if the correct # of minCoins is returned 
		// when choosing the biggest coin will result in 
		// not the most optimal option
		{
			int[] denominations = {1, 3, 6, 7};
			int[] coinsRemaining = {2, 1, 1, 1};
			int value = 9;
			
			int optionsActual = Changemaker.minCoins(denominations, 
					coinsRemaining, value);
			int optionsExpected = 2;
			
			if (optionsActual != optionsExpected) return false;
		}
		
		return true;
	}
	
	/**
	 * This tester method ensures the validity of Changemaker's 
	 * makeChange() BASE call With three scenarios, these tests 
	 * verify the different outputs when a user uses
	 * count and makes non-logical withdrawals
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testMakeChangeBase() {
		
		// (1) makes sure makeChange() returns null when value is negative
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemaining = {0, 1, 1, 2};
			int value = -3;
			
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] expected = null;
			
			if (!Arrays.equals(makeChangeActual, expected)) return false;
		}
		
		// (2) makes sure makeChange() returns null when value 
		// is positive but there's no way to make change
		{
			int[] denominations = {1, 10, 15, 25};
			int[] coinsRemaining = {0, 2, 1, 1};
			int value = 5;
			
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] expected = null;
			
			if (!Arrays.equals(makeChangeActual, expected)) return false;
		}
		
		// (3) makes sure makeChange() returns an ARRAY of all 
		// zeroes when value = 0
		{
			int[] denominations = {1, 3, 6, 7};
			int[] coinsRemaining = {2, 1, 1, 1};
			int value = 0;
			
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] expected = {0, 0, 0, 0};
			
			if (!Arrays.equals(makeChangeActual, expected)) return false;
		}
		
		return true;
		
	}
	
	/**
	 * This tester method ensures the validity of Changemaker's 
	 * makeChange() RECURSIVE call With three scenarios, 
	 * these tests run complex and specific instances to verify
	 * that makeChange() handles callback functions properly
	 * 
	 * @return true if all cases passed, false otherwise
	 */
	public static boolean testMakeChangeRecursive() {
		
		// (1) makes sure that makeChange() returns the most optimal 
		// array when there are three different coins to choose from
		{
			int[] denominations = {1, 5, 10, 25};
			int[] coinsRemaining = {1, 1, 1, 1};
			int value = 40;
			
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] actual = {0, 1, 1, 1};
			
			if (!Arrays.equals(makeChangeActual, actual)) return false;
		}
		
		// (2) makes sure that makeChange returns the most optimal array 
		// even when there are two ways to make up the desired value 
		// with the same number of coins
		{
			int[] denominations = {1, 3, 6, 8};
			int[] coinsRemaining = {1, 1, 1, 1};
			int value = 9;
			
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] actual = {0, 1, 1, 0};
			int[] otherActual = {1, 0, 0, 1};
			
			if (!(Arrays.equals(makeChangeActual, actual) || 
					Arrays.equals(makeChangeActual, otherActual))) return false;
		}
		
		// (3) makes sure makeChange() returns the most optimal array when 
		// choosing the biggest coin isn't the most optimal option
		{
			int[] denominations = {1, 3, 6, 7};
			int[] coinsRemaining = {2, 1, 1, 1};
			int value = 9;
					
			int[] makeChangeActual = Changemaker.makeChange(denominations, 
					coinsRemaining, value);
			int[] actual = {0, 1, 1, 0};
					
			if (!Arrays.equals(makeChangeActual, actual)) return false;
		}
		
		return true;
	}

	/**
	 * 
	 * The main method for the ChangemakerTester class
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("testCountBase is " 
		+ testCountBase() + "!");
		
		System.out.println("testCountRecursive is " 
		+ testCountRecursive() + "!");
		
		System.out.println("testMinCoinsBase is " 
		+ testMinCoinsBase() + "!");
		
		System.out.println("testMinCoinsRecursive is " 
		+ testMinCoinsRecursive() + "!");
		
		System.out.println("testMakeChangeBase is " 
		+ testMakeChangeBase() + "!");
		
		System.out.println("testMakeChangeRecursive is " 
		+ testMakeChangeRecursive() + "!");

	}

}

