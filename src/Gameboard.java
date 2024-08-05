import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gameboard {
    private final int size;
    private ArrayList<String> missed;
    private Map<String, Ship> ships;
    private String[][] board;
    public ArrayList<Ship> shipList;

    public Gameboard(int size) {
        this.size = size;
        this.missed = new ArrayList<>();
        this.ships = new HashMap<>();
        this.board = new String[size][size];
        this.shipList = createShips();
        createBoard();
    }

    private void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "~~";
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean placeShip(String start, String end, Ship ship) {

        int startRow = start.charAt(0) - 'A';
        int startCol = Integer.parseInt(start.substring(1)) - 1;
        int endRow = end.charAt(0) - 'A';
        int endCol = Integer.parseInt(end.substring(1)) - 1;

        if (isPlacementValid(startRow, startCol, endRow, endCol, ship)) {
            // Horizontal
            if (startRow == endRow) {
                for (int col = startCol; col <= endCol; col++) {
                    ships.put("" + start.charAt(0) + (col + 1), ship);
                    board[startRow][col] = ship.symbol;
                }
            // Vertical
            } else {
                for (int row = startRow; row <= endRow; row++) {
                    ships.put((char) ('A' + row) + start.substring(1), ship);
                    board[row][startCol] = ship.symbol;
                }
            }
            return true;
        }
        return false;
    }


    private boolean isPlacementValid(int startRow, int startCol, int endRow, int endCol, Ship ship) {
        // Horizontal
        if (startRow == endRow) {
            return endCol - startCol + 1 == ship.getLength();
        // Vertical
        } else if (startCol == endCol) {
            return endRow - startRow + 1 == ship.getLength();
        }
        return false;
    }

    public Ship takeShot(String position) {
        int row = position.charAt(0) - 'A';
        int col = Integer.parseInt(position.substring(1)) - 1;
        //System.out.println(row + " " + col);
        if (ships.containsKey(position)) {
            Ship ship = ships.get(position);
            //int hitIndex = (row == col ? col : row);
            ship.hit();
            board[row][col] = "XX";
            return ship;

        } else {
            missed.add(position);
            board[row][col] = "oo";
            return null;
        }
    }

    public ArrayList<Ship> createShips(){
        ArrayList<Ship> list = new ArrayList<Ship>();
        // Carrier
        list.add(new Ship(5, "C1"));
        // Battleships
        list.add(new Ship(4, "B1"));
        list.add(new Ship(4, "B2"));
        // Submarine
        list.add(new Ship(3, "S1"));
        list.add(new Ship(3, "S2"));
        list.add(new Ship(3, "S3"));
        // 	Destroyer
        list.add(new Ship(2, "D1"));
        list.add(new Ship(2, "D2"));
        list.add(new Ship(2, "D3"));
        list.add(new Ship(2, "D4"));
        return list;
    };

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

}
