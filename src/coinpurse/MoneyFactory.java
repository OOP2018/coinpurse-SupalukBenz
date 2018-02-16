package coinpurse;

/**
 * MoneyFactory is Singleton which it's superclass of ThaiMoneyFactory
 * and MalayMoneyFactory for creating money
 * @author Supaluk Jaroensuk
 */
public abstract class MoneyFactory {

    /**
     * Attribute of class , as object of MoneyFactory
     */
    private static MoneyFactory factory;

    /**
     * Get instance of MoneyFactory
     * @return subclass of MoneyFactory
     */
    public static MoneyFactory getInstance(){
        return factory;
    }

    /**
     * Create money of object in the local currency
     * @param value is value of money
     * @return new money , as a valuable
     */
    public abstract Valuable createMoney(double value);

    /**
     * Create money of object in the local currency and convert String to double
     * @param value is value of money, as a String
     * @return
     */
    public Valuable createMoney(String value){

        double valueFromString = 0.0;
        try {
            valueFromString = Double.parseDouble(value);
        }catch (Exception e){
            System.out.printf("%s is not a valid currency value%n" , value);
            throw new IllegalArgumentException();
        }
        return createMoney(valueFromString);
    }

    /**
     * Set factory for testing
     * @param f is object of MoneyFactory
     */
    public static void setFactory(MoneyFactory f){
        factory = f;
    }
}
