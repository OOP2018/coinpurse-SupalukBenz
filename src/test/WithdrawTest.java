package test;

import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.strategy.GreedyWithdraw;
import coinpurse.strategy.RecursiveWithdraw;
import coinpurse.strategy.WithdrawStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test withdraw strategy.
 *
 * @author Supaluk Jaroensuk
 */
public class WithdrawTest {
    /**
     * WithdrawStrategy class
     */
    private WithdrawStrategy strategy;

    /** tolerance for comparing two double values */
    private static final double TOL = 1.0E-6;

    /**
     * List of valuable for testing.
     */
    private List<Valuable> moneyValuable = new ArrayList<>();

    /**
     * Money for inserting
     */
    private static final List<Double> money = new ArrayList<>(Arrays.asList(0.50 , 1.0, 2.0 , 5.0 , 10.0 , 20.0 , 50.0 , 100.0));

    /**
     * Amount money of list money.
     */
    private static final double AMOUNT_MONEY = 188.50;

    /**
     * Make money in Baht unit.
     * @param money money for made.
     * @return valuable ,as baht unit.
     */
    public Valuable makeThaiMoney(double money){
        return new Money(money , "Baht");
    }

    /**
     * Initialize strategy and insert money to list.
     */
    @Before
    public void setUp(){
        strategy = new RecursiveWithdraw();
//        strategy = new GreedyWithdraw();
        insertMoney(money);
    }

    /**
     * Insert money to list of money.
     * @param doubles list of money
     */
    public void insertMoney(List<Double> doubles){
        for(Double d : doubles){
            moneyValuable.add(makeThaiMoney(d));
        }
    }

    /**
     * Get the total value of all items in the list money.
     * @param moneyAmount list of money
     * @return current money
     */
    public double amount(List<Valuable> moneyAmount){
        double sum = 0;
        for(Valuable v : moneyAmount){
            sum += v.getValue();
        }
        return sum;
    }

    /**
     * Test withdrawing everything from current money in list.
     */
    @Test
    public void withdrawEverything(){
        Valuable money = makeThaiMoney(AMOUNT_MONEY);
        List<Valuable> withdraw = strategy.withdraw(money , moneyValuable);
        assertEquals(AMOUNT_MONEY , amount(withdraw) , TOL);
    }

    /**
     * Test withdrawing except one item valuable.
     */
    @Test
    public void withdrawExceptOneItem(){
        Valuable money = makeThaiMoney(AMOUNT_MONEY - 50);
        List<Valuable> withdraw = strategy.withdraw(money, moneyValuable);
        assertEquals(AMOUNT_MONEY - 50.0 , amount(withdraw) , TOL);
    }

    /**
     * Test by withdraw amount that more that current money.
     */
    @Test
    public void withdrawMoreThanTotal(){
        Valuable money = makeThaiMoney(AMOUNT_MONEY + 10);
        List<Valuable> withdraw = strategy.withdraw(money , moneyValuable);
        assertNull(withdraw);
    }

    /**
     * Test that money in purse not change when withdraw strategy.
     */
    @Test
    public void withdrawNotChange(){
        List<Valuable> copyList = new ArrayList<Valuable>(moneyValuable);
        Valuable money = makeThaiMoney(20);
        strategy.withdraw(money , copyList);
        assertEquals(amount(moneyValuable) , amount(copyList) , TOL);

    }

    /**
     * Test returning withdraw that equals to amount and in money must be has valuable of amount that is withdrawn.
     */
    @Test
    public void withdrawReturned(){
        Valuable money = makeThaiMoney(25);
        List<Valuable> withdraw = strategy.withdraw(money , moneyValuable);
        assertEquals(25 , amount(withdraw) , TOL);
        for(Valuable v : withdraw){
            assertTrue(moneyValuable.contains(v));
        }

        Valuable money2 = makeThaiMoney(33.5);
        List<Valuable> withdraw2 = strategy.withdraw(money2 , moneyValuable);
        assertEquals(33.5 , amount(withdraw2) , TOL);
        for(Valuable v : withdraw2){
            assertTrue(moneyValuable.contains(v));
        }

    }

    /**
     * Test withdrawing amount that can not withdraw.
     */
    @Test
    public void withdrawImpossible(){
        Valuable money = makeThaiMoney(0.05);
        List<Valuable> withdraw = strategy.withdraw(money , moneyValuable);
        assertNull(withdraw);

        Valuable money2 = makeThaiMoney(-10);
        List<Valuable> withdraw2 = strategy.withdraw(money2 , moneyValuable);
        assertNull(withdraw2);

        List<Valuable> w = new ArrayList<Valuable>();
        w.add(makeThaiMoney(20));
        Valuable money3 = makeThaiMoney(1);
        List<Valuable> withdraw3 = strategy.withdraw(money3 , w);
        assertNull(withdraw3);

    }

    /**
     * Test withdrawing amount equals to zero.
     */
    @Test
    public void withdrawZero(){
        Valuable money = makeThaiMoney(0);
        List<Valuable> withdraw = strategy.withdraw(money,moneyValuable);
        assertNull(withdraw);
    }


}
