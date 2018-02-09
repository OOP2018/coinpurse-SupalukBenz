package coinpurse;

public class Money implements Valuable{
    private double value;
    private String currency;

    public Money(double value , String currency){
        this.value = value;
        this.currency = currency;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getCurrency() {
        return currency;
    }


    /**
     * The equals method will return true if two coins have the same value and currency
     *
     * @param arg object which was compared
     * @return true if two coins are equal and false if two coins are not equal
     */
    public boolean equals(Object arg) {
        if (arg == null) return false;
        if (arg.getClass() != this.getClass()) return false;
        Money c = (Money) arg;
        return getCurrency().equals(c.getCurrency()) && getValue() == c.getValue();
    }

    @Override
    public int compareTo(Valuable o) {
        Money money = (Money) o;
        if(currency.equalsIgnoreCase(o.getCurrency())) return Double.compare(value,o.getValue());
        else return currency.compareToIgnoreCase(money.currency);
    }
}
