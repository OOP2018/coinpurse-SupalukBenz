package coinpurse.strategy;

import coinpurse.MoneyUtil;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Withdraw the requested amount of valuable that implements WithdrawStrategy.
 * @author Supaluk Jaroensuk
 */
public class GreedyWithdraw implements WithdrawStrategy {

    /**
     * Method uses loop for withdrawing money.
     * @param amount is the value that want to withdraw.
     * @param money is a list of current money.
     * @return List that include with coins or banknote of amount
     * and return null if cannot withdraw.
     */
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {

        if(amount.getValue() <= 0 ) return null;

        double amountNeededToWithdraw = amount.getValue();
        double amountTotal = amount.getValue();

        List<Valuable> temp = new ArrayList<Valuable>();
        Comparator<Valuable> comparator = new ValueComparator();

        List<Valuable> filterMoney = MoneyUtil.filterByCurrency(money , amount.getCurrency());

        Collections.sort(filterMoney , comparator);

        for(int i = filterMoney.size() - 1; i >= 0 ; i--){
            amountNeededToWithdraw = amountTotal - filterMoney.get(i).getValue();

            if(amountNeededToWithdraw >= 0){
                temp.add(filterMoney.get(i));
                double sum = 0;
                filterMoney.remove(i);
                if (!temp.isEmpty()) {
                    for (Valuable v : temp) {
                        sum += v.getValue();
                    }
                    amountTotal = amount.getValue() - sum;
                }
            }
            if(amountNeededToWithdraw == 0 || filterMoney.isEmpty()) break;
        }

        if(amountNeededToWithdraw != 0){
            return null;
        }else {
            return temp;
        }
    }
}
