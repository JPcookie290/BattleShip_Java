import java.util.ArrayList;

public class Ship {
    private int length;
    private int hits;
    private ArrayList<Integer> whereHit;
    public boolean sunken;
    public String symbol;

    public Ship(int length, String symbol) {
        this.length = length;
        this.hits = 0;
        this.symbol = symbol;
        this.whereHit = getHitList();
        this.sunken = false;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<Integer> getWhereHit() {
        return whereHit;
    }

    public boolean isSunken() {
        return sunken;
    }

    private ArrayList<Integer> getHitList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < getLength(); i++) {
            list.add(0);
        }
        return list;
    }

    public void hit() {
            hits++;
            if (hits == length) {
                sunken = true;
            }

    }

}
