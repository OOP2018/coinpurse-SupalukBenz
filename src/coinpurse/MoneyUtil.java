package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * MoneyUtil class for test list of valuable by method in class
 * @author Supaluk Jaroensuk
 */
public class MoneyUtil {

    /**
     * check method is method for check compareTo method
     */
    public static void check(){
        List<Valuable> valuables = new ArrayList<>();
        valuables.add(new BankNote(10 , "Yen"));
        valuables.add(new BankNote(1000 ,"Yen"));
        valuables.add(new Coin(1,"Yen"));
        valuables.add(new Coin(0.25,"Yen"));
        valuables.add(new Coin(0.50, "Yen"));
        valuables.add(new Coin(0 , "Bath"));
        valuables.add(new Coin(0, "Dollar"));
        System.out.println("Total coins");
        printCoins(valuables);
        List<Valuable> coinCheckFilter = filterByCurrency(valuables , "Yen");
        sortCoins(coinCheckFilter);
    }

    /**
     * Print the valuable
     * @param v is list in Valuable class
     */
    public static void printCoins(List<Valuable> v){
        for(Valuable valuable : v){
            System.out.printf("%10.2f %s%n" , valuable.getValue() , valuable.getCurrency());
        }
    }

    /**
     * filterByCurrency is method for return list of value that have the same currency
     * @param money is list of Valuable
     * @param currency is currency of value
     * @return list of Valuable that have the same currency
     */
    public static List<Valuable> filterByCurrency(List<Valuable> money , String currency){
        List<Valuable> valuables = new ArrayList<Valuable>();

        for(Valuable c : money){
            if(c.getCurrency().equals(currency)) valuables.add(c);
        }
        return valuables;
    }

    /**
     * Sorting the list of Valuable and printing result
     * @param valuables is list of Valuable
     */
    public static void sortCoins(List<Valuable> valuables){
        Comparator<Valuable> comparable = new ValueComparator();
        Collections.sort(valuables , comparable);
        System.out.println("Sorting...");
        for (Valuable c : valuables){
            System.out.printf("%10.2f - %s%n" , c.getValue() , c.getCurrency());
        }
    }

    public static void main(String[] args) {
        check();
    }
}
