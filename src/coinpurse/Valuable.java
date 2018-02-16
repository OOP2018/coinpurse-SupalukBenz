package coinpurse;

/**
 * Valuable is interface for Coin and BankNote class
 * Contain with abstract method
 * @author Supaluk Jaroensuk
 */
public interface Valuable extends Comparable<Valuable>{

    /**
     * Abstract method for get the value of object
     * @return value of object, as a double
     */
    public double getValue();

    /**
     * Abstract method for get the currency of object
     * @return currency of object, as a String
     */
    public String getCurrency();


}
