import java.util.ArrayList;

public class Ship {
    private int length;
    private int hits;
    private ArrayList<ArrayList<Integer>> currentPos;
    public boolean sunken;
    public String symbol;

    public Ship(int length, String symbol) {
        this.length = length;
        this.hits = 0;
        this.symbol = symbol;
        this.currentPos = new ArrayList<>();
        this.sunken = false;
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

    public void setCurrentPos(int posX, int posY) {
        ArrayList<Integer> positions = new ArrayList<>();
        positions.add(posX);
        positions.add(posY);
        currentPos.add(positions);
    }

    public void hit() {
        hits++;
        if (hits == length) {
            sunken = true;
        }
    }
}

