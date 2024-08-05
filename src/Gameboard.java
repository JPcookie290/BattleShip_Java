import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gameboard {
    private int size;
    private ArrayList<String> missed;
    private Map<String, Ship> ships;
    private String[][] board;

    public Gameboard(int size) {
        this.size = size;
        this.missed = new ArrayList<>();
        this.ships = new HashMap<>();
        this.board = new String[size][size];
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "0";
            }
        }
    }

    public int getSize() {
        return size;
    }

    /*
    public boolean placeShip(String start, String end, Ship ship) {

        int startRow = start.charAt(0) - 'A';
        int startCol = Integer.parseInt(start.substring(1)) - 1;
        int endRow = end.charAt(0) - 'A';
        int endCol = Integer.parseInt(end.substring(1)) - 1;

        if (isPlacementValid(startRow, startCol, endRow, endCol, ship)) {
            if (startRow == endRow) { // Horizontal placement
                for (int col = startCol; col <= endCol; col++) {
                    ships.put("" + start.charAt(0) + (col + 1), ship);
                    board[startRow][col] = ship.symbol;
                }
            } else { // Vertical placement
                for (int row = startRow; row <= endRow; row++) {
                    ships.put("" + (char) ('A' + row) + start.substring(1), ship);
                    board[row][startCol] = ship.symbol;
                }
            }
            return true;
        }
        return false;
    }
    */

    private boolean isPlacementValid(int startRow, int startCol, int endRow, int endCol, Ship ship) {
        // Horizontal
        if (startRow == endRow) {
            return endCol - startCol + 1 == ship.getLength();
        } else if (startCol == endCol) { // Vertical
            return endRow - startRow + 1 == ship.getLength();
        }
        return false;
    }

    public String takeShot(String position) {
        int row = position.charAt(0) - 'A';
        int col = Integer.parseInt(position.substring(1)) - 1;

        if (ships.containsKey(position)) {
            Ship ship = ships.get(position);
            int hitIndex = (row == col ? col : row);
            ship.hit(hitIndex);
            board[row][col] = "X";
            return ship.isSunken() ? "Hit and sunk!" : "Hit!";
        } else {
            missed.add(position);
            board[row][col] = "O";
            return "Miss!";
        }
    }

    public ArrayList<String> getMissedShots() {
        return missed;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Gameboard gameboard = new Gameboard(8);
        gameboard.printBoard();

        Ship ship = new Ship(3, "S");

        gameboard.printBoard();
    }
}
