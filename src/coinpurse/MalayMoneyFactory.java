package coinpurse;

/**
 * Initialize money of MalayMoneyFactory
 * @author Supaluk Jaroensuk
 */
public class MalayMoneyFactory extends MoneyFactory{

    /**
     * Run serial number
     */
    private static long nextSerialNumber = 1000000;

    /**
     * Initialize by call super class
     */
    public MalayMoneyFactory(){
        super();
    }

    /**
     * Specify that value is Sen or Ringgit in the Malay money
     * @param value is value of money
     * @return Sen if value less than 1 and convert value to integer number,
     * Ringgit if value more than or equal 1
     */
    @Override
    public Valuable createMoney(double value) {
        if(value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100){
            BankNote bankNote = new BankNote(value , "Ringgit");
            bankNote.setSerialNumber(nextSerialNumber++);
            return bankNote;
        }
        else if (value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5) {
            return new Coin(value, "Sen");
        }
        throw new IllegalArgumentException("Value is invalid");
    }

}
