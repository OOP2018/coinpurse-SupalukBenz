package coinpurse;

import java.util.Comparator;

/**
 * ValueComparator class is the comparator object
 * @author Supaluk Jaroensuk
 */
public class ValueComparator implements Comparator<Valuable> {

    /**
     * Compare value of two object
     * @param a is object that implement Valuable
     * @param b is object that implement Valuable
     * @return zero if they have same value
     * ,-1 if value of o1 less than value of o2
     * and 1 if value of o1 greater than value of o2
     */
    @Override
    public int compare(Valuable a, Valuable b) {
        int compareNumber = 0;
        if(a.getCurrency().equalsIgnoreCase(b.getCurrency())) {
            if (a.getValue() > b.getValue()) compareNumber = 1;
            else if (a.getValue() < b.getValue()) compareNumber = -1;
        }else {
            compareNumber = a.getCurrency().compareTo(b.getCurrency());
        }

        return compareNumber;
    }
}
