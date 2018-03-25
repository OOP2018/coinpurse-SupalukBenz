package test;

import coinpurse.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test the MoneyFactory by using JUnit
 * @author Supaluk Jaroensuk
 * @version 2018.02.17
 */
public class MoneyFactoryTest {

    /**
     * tolerance for comparing double value
     */
    private static final double TOL = 1.0E-6;

    /**
     * List of money for testing money of Malay, as a double
     */
    private static final List<Double> moneyDoubleMalay = Arrays.asList(0.05 , 0.10
            , 0.2 , 0.5 , 1.0, 2.0 , 5.0 , 10.0 , 20.0 , 50.0 , 100.0);

    /**
     * List of money for testing money of Malay, as a String
     */
    private static final List<String> moneyStringMalay = Arrays.asList("0.05" , "0.10" , "0.20"
            ,"0.5", "1.0" , "2.0" , "5.0" , "10.0" , "20.0" , "50.0" , "100.0");

    /**
     * List of money for testing money of Thai, as a double
     */
    private static final List<Double> moneyDoubleThai = Arrays.asList(1.0 , 2.0 ,5.0 , 10.0 , 20.0
            , 50.0 , 100.0 , 500.0 ,1000.0);

    /**
     * List of money for testing money of Thai, as a String
     */
    private static final List<String> moneyStringThai = Arrays.asList("1.0" , "2.0" , "5.0" , "10.0"
            , "20.0" , "50.0" , "100.0" , "500.0" , "1000.0");

    /**
     * Make a valuable by object of MoneyFactory
     * @param m is MoneyFactory
     * @param value is value of money, as a double
     * @return new Valuable
     */
    private static Valuable makeMoneyDouble(MoneyFactory m , double value){
        return m.createMoney(value);
    }

    /**
     * Make a valuable by object of MoneyFactory
     * @param m is MoneyFactory
     * @param value is value of money, as a String
     * @return new Valuable
     */
    private static Valuable makeMoneyString(MoneyFactory m , String value){
        return m.createMoney(value);
    }

    /**
     * Object of MoneyFactory for testing ThaiMoneyFactory
     */
    private MoneyFactory thaiFactory;

    /**
     * Object of MoneyFactory for testing MalayMoneyFactory
     */
    private MoneyFactory malayFactory;

    /**
     * Insert money to array of valuable
     * @param m is object of MoneyFactory
     * @param money is list of money for testing, as a double
     * @return array of valuable
     */
    private Valuable[] insertMoney(MoneyFactory m , List<Double> money){
        List<Valuable> valuables = new ArrayList<>();
        int i = 0;
        for(Double d : money){
            valuables.add(i , makeMoneyDouble(m , d));
            i++;
        }
        return valuables.toArray(new Valuable[valuables.size()]);
    }

    /**
     * Insert money to array of valuable
     * @param m is object of MoneyFactory
     * @param money is list of money for testing, as a String
     * @return array of valuable
     */
    private Valuable[] insertMoneyString(MoneyFactory m , List<String> money){
        List<Valuable> valuables = new ArrayList<>();
        int i = 0;
        for(String s : money){
            valuables.add(i , makeMoneyString(m , s));
            i++;
        }
        return valuables.toArray(new Valuable[valuables.size()]);
    }

    /**
     * Read a properties file and create MoneyFactory
     * @param classname is name of class
     */
    private void set(String classname){
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
     * Sets up the ThaiMoneyFactory
     */
    @Before
    public void setUpThaiMoneyFactory(){

        set("coinpurse.ThaiMoneyFactory");
        thaiFactory = MoneyFactory.getInstance();
        assertTrue(thaiFactory instanceof ThaiMoneyFactory);

    }

    /**
     * Sets up the MalayMoneyFactory
     */
    @Before
    public void setUpMalayFactory(){

        set("coinpurse.MalayMoneyFactory");
        malayFactory = MoneyFactory.getInstance();
        assertTrue(malayFactory instanceof MalayMoneyFactory);

    }

    /**
     * Test creating money of ThaiMoneyFactory by value, as a double
     */
    @Test
    public void testCreateThaiMoneyDouble(){

        Valuable[] thaiMoney = insertMoney(thaiFactory , moneyDoubleThai);
        assertEquals(moneyDoubleThai.get(0), thaiMoney[0].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(1), thaiMoney[1].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(2), thaiMoney[2].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(5), thaiMoney[5].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(8), thaiMoney[8].getValue() ,TOL);

    }

    /**
     * Test creating money of MalayMoneyFactory by value, as a double
     */
    @Test
    public void testCreateMalayMoneyDouble(){

        Valuable[] malayMoney = insertMoney(malayFactory , moneyDoubleMalay);
        assertEquals(moneyDoubleMalay.get(0), malayMoney[0].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(1), malayMoney[1].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(2), malayMoney[2].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(3), malayMoney[3].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(5), malayMoney[5].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(8), malayMoney[8].getValue() ,TOL);

    }

    /**
     * Test creating money of ThaiMoneyFactory by value, as a String
     */
    @Test
    public void testCreateThaiMoneyString(){

        Valuable[] thaiMoney = insertMoneyString(thaiFactory , moneyStringThai);
        assertEquals(moneyDoubleThai.get(0), thaiMoney[0].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(1), thaiMoney[1].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(2), thaiMoney[2].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(5), thaiMoney[5].getValue() ,TOL);
        assertEquals(moneyDoubleThai.get(8), thaiMoney[8].getValue() ,TOL);

    }

    /**
     * Test creating money of MalayMoneyFactory by value, as a double
     */
    @Test
    public void testCreateMalayString(){

        Valuable[] malayMoney = insertMoneyString(malayFactory , moneyStringMalay);
        assertEquals(moneyDoubleMalay.get(0), malayMoney[0].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(1), malayMoney[1].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(2), malayMoney[2].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(3), malayMoney[3].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(5), malayMoney[5].getValue() ,TOL);
        assertEquals(moneyDoubleMalay.get(8), malayMoney[8].getValue() ,TOL);

    }

    /**
     * Test currency of Malay money between Sen and Ringgit
     */
    @Test
    public void testCurrencyMalay(){

        Valuable[] malayMoney = insertMoneyString(malayFactory , moneyStringMalay);
        Valuable check1 = new Coin(0.05 , "Sen");
        assertEquals(check1, malayMoney[0]);

        Valuable check2 = new Coin(0.10 , "Sen");
        assertEquals(check2, malayMoney[1]);

        Valuable check3 = new Coin(0.20 , "Sen");
        assertEquals(check3, malayMoney[2]);

        Valuable check4 = new BankNote(20.0 , "Ringgit");
        assertEquals(check4, malayMoney[8]);

    }

    /**
     * Test that a serial number running or not
     */
    @Test
    public void testSerialNumber(){

        Valuable[] thaiMoney = insertMoney(thaiFactory , moneyDoubleThai);
        assertTrue(thaiMoney[7].compareTo(thaiMoney[8]) < 0);

        Valuable[] malayMoney = insertMoney(malayFactory , moneyDoubleMalay);
        assertTrue(malayMoney[4].compareTo(malayMoney[5]) < 0);

    }

}