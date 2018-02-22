package coinpurse;

/**
 * Initialize money of ThaiMoneyFactory
 * @author Supaluk Jaroensuk
 */
public class ThaiMoneyFactory extends MoneyFactory{

    /**
     * Run serial number
     */
    private static long nextSerialNumber = 1000000;

    /**
     * Initialize by call super class
     */
    public ThaiMoneyFactory(){
        super();
    }

    /**
     * Specify that value is coin or banknote
     * @param value is value of money
     * @return Coin if value less than 2 , Banknote if value more than or equal 20
     */
    @Override
    public Valuable createMoney(double value) {
        if(value == 20 || value == 50 || value == 100 || value == 500 || value == 1000) {
            BankNote bankNote = new BankNote(value , "Ringgit");
            bankNote.setSerialNumber(nextSerialNumber++);
            return bankNote;

        }else if(value == 1 || value == 2 || value == 5 || value == 10){
            return new Coin(value , "Baht");

        }

        throw new IllegalArgumentException("Value is invalid");
    }
}
