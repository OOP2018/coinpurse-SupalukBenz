package coinpurse.strategy;

import coinpurse.Valuable;

import java.util.List;

/**
 * WithdrawStrategy contains a method withdraw that other class can implement for using the same method.
 * @author Supaluk Jaroensuk
 */
public interface WithdrawStrategy {

    /**
     * Method is a abstract method for withdrawing money.
     * @param amount is the value that want to withdraw.
     * @param money is a list of current money.
     * @return List that include with coins or banknote of amount
     * and return null if cannot withdraw.
     */
    public List<Valuable> withdraw(Valuable amount , List<Valuable> money);

}
