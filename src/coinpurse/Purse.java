package coinpurse;

import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 *  A coin purse contains coins and banknotes.
 *  You can insert coins or banknotes, withdraw money, check the balance,
 *  and check if the purse is full.
 *
 *  @author Supaluk Jaroensuk
 */
public class Purse {

    /** Collection of objects in the purse. */
    private List<Valuable> money = new ArrayList<Valuable>();

    /** Capacity is maximum number of items the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;

    /**
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of valuables you can put in purse.
     */
    public Purse( int capacity ) {
        this.capacity = capacity;
    }

    /**
     * Count and return the number of valuable in the purse.
     * This is the number of valuable, not their value.
     * @return the number of valuable in the purse
     */
    public int count() {
        return money.size();
    }

    /**
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
		double total = 0.0;
		for(Valuable v : money){
		    total += v.getValue();
        }
        return total;
    }


    /**
     * Return the capacity of the purse.
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
     * Insert a valuable into the purse.
     * The valuable is only inserted if the purse has space for it
     * and the valuable has positive value.
     * @param v is a valuable object to insert into purse
     * @return true if valuable inserted, false if can't insert
     */
    public boolean insert(Valuable v) {
        if(isFull() || v.getValue() <= 0) {
            return false;
        } else {
            money.add(v);
            return true;
        }
    }

    /**
     *  Withdraw the requested amount of money.
     *  Return an array of valuable withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Valuable objects for money withdrawn,
	 *    or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw( double amount ) {

        double amountNeededToWithdraw = amount;

        List<Valuable> temp = new ArrayList<Valuable>();
        Comparator<Valuable> comparable = new ValueComparator();
        Collections.sort(money , comparable);
        double amountTotal = amount;
        int size = money.size();
        if(amount < 0 || amount > getBalance()){
            return null;
        }

        for (int i = size - 1; i >= 0; i--) {
            amountNeededToWithdraw = amountTotal - money.get(i).getValue();
            if (amountNeededToWithdraw >= 0) {
                double sum = 0.0;
                temp.add(money.get(i));
                money.remove(i);
                if (!temp.isEmpty()) {
                    for (Valuable c : temp) {
                        sum += c.getValue();
                    }
                    amountTotal = amount - sum;
                }
            }
            if (amountNeededToWithdraw == 0 || money.isEmpty()) break;
        }
        if (amountNeededToWithdraw > 0 || amountNeededToWithdraw < 0) {
            money.addAll(temp);
            return null;

        }else {
                return temp.toArray(new Valuable[temp.size()]);
            }

	}

    /**
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
    	return String.format("Total %d , value %.2f" , count() , getBalance());
    }

}
