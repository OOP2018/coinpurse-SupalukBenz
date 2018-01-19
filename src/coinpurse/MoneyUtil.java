package coinpurse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MoneyUtill class for test method of Coin class
 * @author Supaluk Jaroensuk
 */
public class MoneyUtil {

    /**
     * sortCoins method is method for check compareTo method
     */
    public static void sortCoins(){
        List<Coin> coins = new ArrayList<Coin>();
        coins.add(new Coin(10 , "Yen"));
        coins.add(new Coin(1000 ,"Yen"));
        coins.add(new Coin(1,"Yen"));
        coins.add(new Coin(0.25,"Yen"));
        coins.add(new Coin(0.50, "Yen"));
        coins.add(new Coin(0 , "Bath"));
        coins.add(new Coin(0, "Dollar"));
        System.out.println("Total coins");
        printCoins(coins);
        List<Coin> coinCheckFiter = filterByCurrency(coins , "Yen");
        sortCoins(coinCheckFiter);
    }

    /**
     * Print the coins
     * @param c is list in Coin class
     */
    public static void printCoins(List<Coin> c){
        for(Coin coins : c){
            System.out.printf("%10.2f %s%n" , coins.getValue() , coins.getCurrency());
        }
    }

    /**
     * filterByCurrency is method for return list of value that have the same currency
     * @param coins is list of Coin
     * @param currency is currency of value
     * @return list of Coin that have the same currency
     */
    public static List<Coin> filterByCurrency(List<Coin> coins , String currency){
        List<Coin> coin = new ArrayList<Coin>();
        System.out.println("Currency : " + currency);
        for(Coin c : coins){
            if(c.getCurrency().equals(currency)) coin.add(c);
        }
        return coin;
    }

    /**
     * Sorting the list of Coin and printing result
     * @param coins is list of Coin
     */
    public static void sortCoins(List<Coin> coins){
        Collections.sort(coins);
        System.out.println("Sorting...");
        for (Coin c : coins){
            System.out.printf("%10.2f - %s%n" , c.getValue() , c.getCurrency());
        }
    }


    public static void main(String[] args) {
        sortCoins();
    }
}
