package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test methods of the MoneyFactory and print results on the console
 */
public class MoneyFactoryDemo {

    /**
     * Object of MoneyFactory for testing ThaiMoneyFactory
     */
    private static MoneyFactory thaiMoneyTest;

    /**
     * Object of MoneyFactory for testing MalayMoneyFactory
     */
    private static MoneyFactory malayMoneyTest;

    /**
     * Read a properties file and create MoneyFactory
     * @param classname is name of class
     */
    public static void set(String classname){
        MoneyFactory factory = null;
        try {
            factory = (MoneyFactory)Class.forName(classname).newInstance();
        } catch (ClassCastException cce) {
            System.out.println(classname + " is not type MoneyFactory");
        } catch (Exception e) {
            System.out.println("Error creating MoneyFactory " + e.getMessage());
        }
        if(factory == null) System.exit(0);

        MoneyFactory.setFactory(factory);

    }

    /**
     * Make a valuable by object of MoneyFactory
     * @param m is MoneyFactory
     * @param value is value of money, as a double
     * @return new Valuable
     */
    public static Valuable makeMoneyDouble(MoneyFactory m , double value){
        return m.createMoney(value);
    }

    /**
     * Insert money to array of valuable
     * @param m is object of MoneyFactory
     * @param money is list of money for testing, as a double
     * @return array of valuable
     */
    public static Valuable[] insertMoney(MoneyFactory m , List<Double> money){
        List<Valuable> valuables = new ArrayList<>();
        int i = 0;
        for(Double d : money){
            valuables.add(i , makeMoneyDouble(m , d));
            i++;
        }
        return valuables.toArray(new Valuable[valuables.size()]);
    }

    /**
     * Print total money in array of valuable
     * @param name is name of class
     * @param money is array of valuable
     */
    public static void showMoney(String name,Valuable[] money){
        System.out.println(name + " have total money... ");
        for(Valuable y : money){
            System.out.print(y);
        }
    }

    /**
     * Test method of the ThaiMoneyFactory class
     * @param money is array list of money, as a double
     */
    public static void testThaiFactory(List<Double> money){
        set("coinpurse.ThaiMoneyFactory");
        thaiMoneyTest = MoneyFactory.getInstance();
        Valuable[] thaiMoney = insertMoney(thaiMoneyTest , money);
        showMoney("Thai money factory" , thaiMoney);
    }

    /**
     * Test method of the MalayMoneyFactory class
     * @param money is array list of money, as a double
     */
    public static void testMalayFactory(List<Double> money){
        set("coinpurse.MalayMoneyFactory");
        malayMoneyTest = MoneyFactory.getInstance();
        Valuable[] malayMoney = insertMoney(malayMoneyTest , money);
        showMoney("Malay money factory" , malayMoney);
    }

    public static void main(String[] args) {

        List<Double> moneyThai  = Arrays.asList(1.0 , 2.0 ,5.0 , 10.0 , 20.0
                , 50.0 , 100.0 , 500.0 ,1000.0);
        List<Double> moneyMalay = Arrays.asList(1.0, 2.0 , 5.0 , 10.0 , 20.0 , 50.0 , 100.0);
        List<Double> moneyTestSen = Arrays.asList(0.05 , 0.10 , 0.20 , 0.50);
        System.out.println("Test Baht");
        System.out.println("Money for adding " + moneyThai);
        testThaiFactory(moneyThai);
        System.out.println();
        System.out.println("Test Sen");
        System.out.println("Money Sen for adding " + moneyTestSen);
        testMalayFactory(moneyTestSen);
        System.out.println();
        System.out.println("Test Ringgit");
        System.out.println("Money Ringgit for adding " + moneyMalay);
        testMalayFactory(moneyMalay);
    }
}
