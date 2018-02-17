package coinpurse;

/**
 * Initialize money of MalayMoneyFactory
 * @author Supaluk Jaroensuk
 */
public class MalayMoneyFactory extends MoneyFactory{

    /**
     * Run unique serial number
     */
    public MalayMoneyFactory(){
        BankNote.setSerialNumber(1000000);
    }

    /**
     * Specify that value is Sen or Ringgit in the Malay money
     * @param value is value of money
     * @return Sen if value less than 1 and convert value to integer number,
     * Ringgit if value more than or equal 1
     */
    @Override
    public Valuable createMoney(double value) {
        if(value < 1) return new Coin(value*100 , "Sen");
        return new BankNote(value , "Ringgit");
    }

}
