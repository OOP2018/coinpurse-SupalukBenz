package coinpurse;

/**
 * Initialize money of ThaiMoneyFactory
 * @author Supaluk Jaroensuk
 */
public class ThaiMoneyFactory extends MoneyFactory{

    /**
     * Specify that value is coin or banknote
     * @param value is value of money
     * @return Coin if value less than 2 , Banknote if value more than or equal 20
     */
    @Override
    public Valuable createMoney(double value) {
        if(value >= 20) return new BankNote(value , "Baht");
        return new Coin(value , "Baht");
    }
}
