package coinpurse.strategy;

import coinpurse.Money;
import coinpurse.MoneyUtil;
import coinpurse.Valuable;

import java.util.ArrayList;
import java.util.List;

/**
 * Withdraw strategy by using recursive.
 * @author Supaluk Jaroensuk
 */
public class RecursiveWithdraw implements WithdrawStrategy{

    /**
     * Method uses recursive for withdrawing money.
     * @param amount is the value that want to withdraw.
     * @param money is a list of current money.
     * @return List that include with coins or banknote of amount
     * and return null if cannot withdraw.
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        if(amount.getValue() == 0 || money.size() <= 0) return null;

        List<Valuable> filterMoney = MoneyUtil.filterByCurrency(money , amount.getCurrency());

        return helperRecursive(amount,filterMoney);
    }

    /**
     * Helper recursive method runs by decreasing amount and list of money until can be withdrawn or
     * if not it will return null.
     * @param amount is value that want to withdraw.
     * @param money is list of current money.
     * @return Recursion until get list of money that want to withdraw if not it will return null.
     */
    public List<Valuable> helperRecursive(Valuable amount , List<Valuable> money){
        if(amount.getValue() == 0) return new ArrayList<Valuable>();
        if(money.size() <= 0 || amount.getValue() <= 0) return null;

        double newAmount = amount.getValue() - money.get(0).getValue();
        Valuable valuableNew = new Money(newAmount , amount.getCurrency());
        List<Valuable> listAmount = helperRecursive(valuableNew , money.subList(1,money.size()));

        if(listAmount == null) {
            return helperRecursive(amount , money.subList(1,money.size()));
        }
        else {
            listAmount.add(money.get(0));
            return listAmount;
        }
    }


}
