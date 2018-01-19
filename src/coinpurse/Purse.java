package coinpurse;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
/**
 *  A coin purse contains coins.
 *  You can insert coins, withdraw money, check the balance,
 *  and check if the purse is full.
 *
 *  @author Supaluk Jaroensuk
 */
public class Purse {
    /** Collection of objects in the purse. */
    private List<Coin> money = new ArrayList<Coin>();

    /** Capacity is maximum number of items the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;

    /**
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of coins you can put in purse.
     */
    public Purse( int capacity ) {
        this.capacity = capacity;
    }

    /**
     * Count and return the number of coins in the purse.
     * This is the number of coins, not their value.
     * @return the number of coins in the purse
     */
    public int count() {
        int countCoins = 0;
        for(Coin c : money){
            countCoins++;
        }
        return countCoins;
    }

    /**
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
		double total = 0.0;
		for(Coin c : money){
		    total += c.getValue();
        }
        return total;
    }


    /**
     * Return the capacity of the coin purse.
     * @return the capacity
     */
    public int getCapacity() {
		return this.capacity;
	}

    /**
     *  Test whether the purse is full.
     *  The purse is full if number of items in purse equals
     *  or greater than the purse capacity.
     *  @return true if purse is full.
     */
    public boolean isFull() {
        return capacity == count();
    }

    /**
     * Insert a coin into the purse.
     * The coin is only inserted if the purse has space for it
     * and the coin has positive value.  No worthless coins!
     * @param coin is a Coin object to insert into purse
     * @return true if coin inserted, false if can't insert
     */
    public boolean insert( Coin coin ) {
        if(isFull() || coin.getValue() <= 0) {
            return false;
        } else {
            money.add(coin);
            return true;
        }
    }

    /**
     *  Withdraw the requested amount of money.
     *  Return an array of Coins withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Coin objects for money withdrawn,
	 *    or null if cannot withdraw requested amount.
     */
    public Coin[] withdraw( double amount ) {
	   /*
		* See lab sheet for outline of a solution,
		* or devise your own solution.
		* The idea is to be greedy.
		* Try to withdraw the largest coins possible.
		* Each time you choose a coin as a candidate for
		* withdraw, add it to a temporary list and
		* decrease the amount (remainder) to withdraw.
		*
		* If you reach a point where amountNeededToWithdraw == 0
		* then you found a solution!
		* Now, use the temporary list to remove coins
		* from the money list, and return the temporary
		* list (as an array).
		*/

		// Did we get the full amount?
		// This code assumes you decrease amount each time you remove a coin.
    	// Your code might use some other variable for the remaining amount to withdraw.
        double amountNeededToWithdraw = amount;
        List<Coin> value = new ArrayList<Coin>();
        List<Coin> coins = new ArrayList<Coin>();
        Collections.sort(money);
        value.addAll(money);
        double amountTotal = amount;

        if(amount < 0 || amount > getBalance()){
            return null;
        }

        for (int i = value.size() - 1; i >= 0; i--) {
            amountNeededToWithdraw = amountTotal - value.get(i).getValue();
            if (amountNeededToWithdraw >= 0) {
                double sum = 0.0;
                coins.add(value.get(i));
                money.remove(i);
                if (!coins.isEmpty()) {
                    for (Coin c : coins) {
                        sum += c.getValue();
                    }
                    amountTotal = amount - sum;
                }
            }
            if (amountNeededToWithdraw == 0 || money.isEmpty()) break;
        }
        if (amountNeededToWithdraw > 0 || amountNeededToWithdraw < 0) {
            money.addAll(coins);
            return null;

        }else {
                return coins.toArray(new Coin[value.size()]);
            }


		// Success.
		// Remove the coins you want to withdraw from purse,
		// and return them as an array.
		// Use list.toArray( array[] ) to copy a list into an array.
		// toArray returns a reference to the array itself.

	}

    /**
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
    	return String.format("Total %d coins , value %.2f" , count() , getBalance());
    }

}
