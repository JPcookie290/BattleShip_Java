import java.util.ArrayList;

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

    /* ---------- Setter functions ---------- */
    public void setCurrentPos(int row, int col) {
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(row);
        positions.add(col);
        currentPos.add(positions);
    }

    /* ---------- Getter functions ---------- */
    public int getLength() {
        return length;
    }

    public ArrayList<ArrayList<Integer>> getCurrentPos() {
        return currentPos;
    }

    /* ---------- return boolean for check in gameboard, if ship is sunken ---------- */
    public boolean isSunken() {
        return sunken;
    }

    /* ---------- hit function ---------- */
    public void hit() {
        hits++;
        if (hits == length) {
            sunken = true;
        }
    }
}

