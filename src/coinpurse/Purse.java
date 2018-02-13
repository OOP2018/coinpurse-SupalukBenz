package coinpurse;

import java.util.*;

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
        return count() >= capacity;
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
     *  Withdraw the requested amount of money which currency is Baht.
     *  Return an array of valuable withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Valuable objects for money withdrawn,
	 *    or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw( double amount ) {
        Valuable valuable = new Money(amount , "Baht");
        return withdraw(valuable);
	}

    /**
     * Withdraw the requested amount of Valuable
     * Can withdraw when amount has the same currency with money
     * @param amount is amount that need to withdraw
     * @return Array that include with coins or banknote of amount
     * and return null of cannot withdraw
     */
	public Valuable[] withdraw(Valuable amount){

        double amountNeededToWithdraw = amount.getValue();
        double amountTotal = amount.getValue();

        List<Valuable> temp = new ArrayList<Valuable>();
        Comparator<Valuable> comparator = new ValueComparator();

        List<Valuable> filterMoney = MoneyUtil.filterByCurrency(money , amount.getCurrency());
        money.removeAll(filterMoney);
        Collections.sort(filterMoney , comparator);

        for(int i = filterMoney.size() - 1; i >= 0 ; i--){
            amountNeededToWithdraw = amountTotal - filterMoney.get(i).getValue();
            if(amountNeededToWithdraw >= 0){
                temp.add(filterMoney.get(i));
                filterMoney.remove(i);
                double sum = 0;
                if (!temp.isEmpty()) {
                    for (Valuable c : temp) {
                        sum += c.getValue();
                    }
                    amountTotal = amount.getValue() - sum;
                }
            }
            if(amountNeededToWithdraw == 0 || filterMoney.isEmpty()) break;
        }

        if(amountNeededToWithdraw > 0 || amountNeededToWithdraw < 0){
            filterMoney.addAll(temp);
            money.addAll(filterMoney);
            return null;
        }else {
            money.addAll(filterMoney);
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
