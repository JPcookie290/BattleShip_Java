import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    private final int length;
    private int hits;
    private ArrayList<ArrayList<Integer>> currentPos;
    public boolean sunken;
    public String symbol;
    public String title;

    public Ship(int length, String symbol, String title) {
        this.length = length;
        this.hits = 0;
        this.symbol = symbol;
        this.currentPos = new ArrayList<>();
        this.sunken = false;
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public ArrayList<ArrayList<Integer>> getCurrentPos() {
        return currentPos;
    }

    public boolean isSunken() {
        return sunken;
    }

    public void setCurrentPos(int row, int col) {
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(row);
        positions.add(col);
        currentPos.add(positions);
    }

    public void hit(int row, int col) {
        /*
        ArrayList<Integer> takenHit = new ArrayList<>();
        takenHit.add(row);
        takenHit.add(col);
        currentPos.set(currentPos.indexOf(takenHit), new ArrayList<>(Arrays.asList( -1, -1)));
        */

        hits++;
        if (hits == length) {
            sunken = true;
        }
    }
}

