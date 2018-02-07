package coinpurse;

/**
 * BankNote class include value, currency and get serial number
 * @author Supaluk Jaroensuk
 */
public class BankNote implements Valuable{

    /**
     * A value of money
     */
    private double value;

    /**
     * A currency of value
     */
    private String currency;

    /**
     * A unique serial number
     */
    private long serialNumber;

    /**
     * String serial number
     */
    private static long nextSerialNumber = 1000000;

    /**
     * Initialize money and run a unique serial number
     * @param value is user's amount
     * @param currency is currency of value
     */
    public BankNote(double value, String currency) {
        this.value = value;
        this.currency = currency;
        this.serialNumber = nextSerialNumber;
        nextSerialNumber++;
    }

    /**
     * Get the user's value
     * @return value, as a double
     */
    public double getValue(){
        return value;
    }

    /**
     * Get the currency of money
     * @return money, as a String
     */
    public String getCurrency(){
        return currency;
    }

    /**
     * Get each serial number of money
     * @return serial number, as a long
     */
    public long getSerial(){
        return serialNumber;
    }

    /**
     * The equals method will return true if two coins have the same value and currency
     * @param obj is object which was compared
     * @return true if two coins are equal and false if two coins are not equal
     */
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        BankNote b = (BankNote) obj;
        return b.value == value && b.currency.equals(currency);
    }

    /**
     * Show amount of money
     * @return show amount of money, as include with value and currency
     */
    public String toString(){
        return String.format("%.2f-%s note [%d]" , value , currency , serialNumber);
    }
}