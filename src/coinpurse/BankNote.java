package coinpurse;

/**
 * BankNote class include value, currency and get serial number
 * @author Supaluk Jaroensuk
 */
public class BankNote extends Money{
    /**
     * A unique serial number
     */
    private long serialNumber;

    /**
     * String serial number
     */

    /**
     * Initialize money and run a unique serial number
     * @param value is user's amount
     * @param currency is currency of value
     */
    public BankNote(double value, String currency) {
        super(value , currency);
    }

    /**
     * For specify a serial number of each factory
     * @param serialNumber is initialize a serial number
     */
    public void setSerialNumber(long serialNumber){
        this.serialNumber = serialNumber;
    }

    /**
     * Get each serial number of money
     * @return serial number, as a long
     */
    public long getSerial(){
        return serialNumber;
    }

    /**
     * Show amount of money
     * @return show amount of money, as include with value and currency
     */
    public String toString(){
        return String.format("%.2f %s note [%d]%n" ,getValue() , getCurrency() , serialNumber);
    }
}