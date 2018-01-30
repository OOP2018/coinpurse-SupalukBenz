package coinpurse;

import java.util.Comparator;

/**
 * ValueComparator class is the comparator object
 * @author Supaluk Jaroensuk
 */
public class ValueComparator implements Comparator<Valuable> {

    /**
     * Compare value of two object
     * @param o1 is object that implement Valuable
     * @param o2 is object that implement Valuable
     * @return zero if they have same value
     * ,-1 if value of o1 less than value of o2
     * and 1 if value of o1 greater than value of o2
     */
    public int compare(Valuable o1, Valuable o2) {
        if(o1.getValue() > o2.getValue()) return 1;
        else if(o1.getValue() < o2.getValue()) return -1;
        return 0;
    }
}
