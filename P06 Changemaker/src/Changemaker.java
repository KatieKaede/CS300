/**
 * 
 * @author katiekrause
 *
 */
public class Changemaker {
	
	/**
	 * 
	 * @param denominations
	 * @param coinsRemaining
	 * @param value
	 * @return
	 */
	public static int count(int[] denominations, int[] coinsRemaining, int value) {
		if (value < 0) {
			return 0;
			
		} else if (value == 0) {
			return 1;
			
		} else {
		
		int differentOptions = 0;
		
		for (int i = 0; i < denominations.length; i++) {
			if (coinsRemaining[i] > 0 && value >= denominations[i]) {
				coinsRemaining[i]--;
				
			differentOptions += count(denominations, coinsRemaining, value - denominations[i]);
			
			coinsRemaining[i]++;
			}
		}
		
	return differentOptions;
	
		}
	}
	
	
	public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
		if (value < 0) {
			return -1;
		}
		
		if (value == 0) {
			return 0;
		}

	    int minCoins = Integer.MAX_VALUE;

	    for (int i = 0; i < denominations.length; i++) {
	        if (coinsRemaining[i] > 0 && value >= denominations[i]) {
	            coinsRemaining[i]--;

	            int subMincoins = minCoins(denominations, coinsRemaining, value - denominations[i]);

	            if (subMincoins >= 0 && subMincoins < minCoins) {
	                minCoins = subMincoins + 1;
	            }

	            coinsRemaining[i]++;
	        }
	    }

	    if (minCoins == Integer.MAX_VALUE) {
	        return -1; // no valid solution found
	        
	    } else {
	        return minCoins;
	    }
	}

	public static int[] makeChange(int[] denominations, int[] coinsRemaining, 
			int value) {
		if (value < 0) {
			return null;
		}
		
		if (value == 0) {
			int[] emptyArray = new int[denominations.length];
			return emptyArray;
		}
		
		int[] optimalCoins = null;
		int change = Integer.MAX_VALUE;
		for (int i = 0; i < denominations.length; i++) {	
			if (coinsRemaining[i] > 0 && value >= denominations[i]) {
		            coinsRemaining[i]--;
		            
		            int[] remainingCoins = makeChange(denominations, coinsRemaining, 
		            		value - denominations[i]);
		            
		            if (remainingCoins != null) {
		            	remainingCoins[i]++;
		            	int totalCoins = 0;
		            	for (int j = 0; j < remainingCoins.length; j++) {
		            		totalCoins += remainingCoins[j];
		            }
		            if (totalCoins < change) {
		            	optimalCoins = remainingCoins;
		            	change = totalCoins;
		            }
		        }
		        
		        coinsRemaining[i]++;
			}
		}
		
		if (optimalCoins == null) {
			return null; 
		} else {
			return optimalCoins;
		}
	}
}
