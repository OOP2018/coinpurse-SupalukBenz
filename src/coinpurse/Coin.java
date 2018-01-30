package coinpurse;

/**
 * Coin class include with value and currency
 * @author Supaluk Jaroensuk
 */
public class Coin implements Valuable{
    /**
     * A value of money
     */
    private double value;

    /**
     * Currency of money
     */
    private String currency;

    /**
     * Initialize money
     * @param value is user's amount
     * @param currency is value's currency
     */
    public Coin(double value , String currency){
        if(value > 0) this.value = value;
        this.currency = currency;
    }

    /**
     * Get the user's value
     * @return value, as a double
     */
    public double getValue(){
        return value;
    }

    /**
     * Get the value's currency
     * @return currency of value, as a String
     */
    public String getCurrency(){
        return currency;
    }

    /**
     * The equals method will return true if two coins have the same value and currency
     * @param arg object which was compared
     * @return true if two coins are equal and false if two coins are not equal
     */
    public boolean equals(Object arg){
        if(arg == null) return false;
        if(arg.getClass() != this.getClass()) return false;
        Coin c = (Coin)arg;
        return currency.equals(c.currency) && value == c.value;
    }

    /**
     * Show amount of money
     * @return show amount of money, as include with value and currency
     */
    public String toString(){
        return String.format("%.2f - %s%n" , value , currency);
    }

    /**
     * Used for sort in java.util.Collections
     * @param o is Coin class
     * @return zero if they have same value and -1 if value less than value of parameter
     * and 1 if value greater than value of parameter
     */
    public int compareTo(Coin o) {
        if(o.value == value) return 0;
        if(o.value > value) return -1;
        return 1;
    }
}
