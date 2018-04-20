package coinpurse;

import java.lang.reflect.Array;
import java.util.*;

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
        sortMoney(coinCheckFilter);
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
     * @param valuables is list of Valuable
     * @param currency is currency of value
     * @return list of Valuable that have the same currency
     */
    public static <E extends Valuable>List<E> filterByCurrency(List<E> valuables , String currency){
        List<E> money = new ArrayList<>();
        for(E c : valuables){
            if(c.getCurrency().equals(currency)) money.add(c);
        }
        return money;
    }

    /**
     * Sorting the list of Valuable and printing result
     * @param valuables is list of Valuable
     */
    public static void sortMoney(List<? extends Valuable> valuables){

        Comparator<Valuable> comparable = new ValueComparator();
        Collections.sort(valuables , comparable);

        System.out.println("Sorting...");
        for (Valuable c : valuables){
            System.out.printf("%10.2f - %s%n" , c.getValue() , c.getCurrency());
        }
    }

    public static <E extends Comparable<? super E>>E max(E ... args){
        E max = args[0];
        int size = args.length - 1;
        for(int i=0; i<=size; i++){
            int compare = args[i].compareTo(max);
            if(compare > 0) max = args[i];
        }

        return max;
    }

    public static void main(String[] args) {
        check();

        Money m1 = new BankNote(100, "Baht");
        Money m2 = new BankNote(500, "Baht");
        Money m3 = new Coin(20, "Baht");
        String m = max("dog", "zebra", "cat");
        Money maxMoney = max(m1, m2, m3);
        System.out.printf("Test sorting %s, %s, %s = %s\n", "dog", "zebra", "cat" , m);
        System.out.printf("Test sorting %s, %s, %s = %s", m1.toString(), m2.toString(), m3.toString(), maxMoney.toString());



    }
}
