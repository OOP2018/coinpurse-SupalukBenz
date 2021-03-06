package coinpurse;

/**
 * Coin class include with value and currency
 * @author Supaluk Jaroensuk
 */
public class Coin extends Money {
    /**
     * Initialize money
     * @param value    is user's amount
     * @param currency is value's currency
     */
    public Coin(double value, String currency) {
        super(value, currency);
    }

    /**
     * Show amount of money
     *
     * @return show amount of money, as include with value and currency
     */
    public String toString() {
        if(getCurrency().equals("Sen")) return String.format("%.2f - %s", getValue()*100, getCurrency());
        return String.format("%.2f - %s", getValue(), getCurrency());
    }

}

