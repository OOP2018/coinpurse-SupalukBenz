package coinpurse;

/**
 * Money class implements Valuable for
 * both Coin and BankNote can extend to avoid duplicate code
 * @author Supaluk Jaroensuk
 */
public class Money implements Valuable{
    private double value;
    private String currency;

    /**
     * Initialize money
     * @param value is user's amount
     * @param currency is currency of money
     */
    public Money(double value , String currency){
        this.value = value;
        this.currency = currency;
    }

    /**
     * Get value of money's user
     * @return value ,as a double
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Get currency of money
     * @return currency ,as a String
     */
    @Override
    public String getCurrency() {
        return currency;
    }


    /**
     * The equals method will return true if two coins have the same value and currency
     *
     * @param arg object which was compared
     * @return true if two coins are equal and false if two coins are not equal
     */
    public boolean equals(Object arg) {
        if (arg == null) return false;
        if (arg.getClass() != this.getClass()) return false;
        Money c = (Money) arg;
        return getCurrency().equals(c.getCurrency()) && getValue() == c.getValue();
    }

    /**
     * Comparing between 2 Money include of value and currency
     * @param o is object that implement Valuable
     * @return true if they have the same value and same currency
     * and false if they have different value and currency
     */
    @Override
    public int compareTo(Valuable o) {
        Money money = (Money) o;
        if(currency.equalsIgnoreCase(o.getCurrency())) return Double.compare(value,o.getValue());
        else return currency.compareToIgnoreCase(money.currency);
    }
}
