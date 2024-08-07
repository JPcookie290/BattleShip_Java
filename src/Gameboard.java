import java.util.ArrayList;


public class Gameboard {
    private final int size;
    private ArrayList<String> missed;
    String[][] board;
    private ArrayList<Ship> shipList;

    public Gameboard(int size) {
        this.size = size;
        this.missed = new ArrayList<>();
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

    public boolean placeShip(int startRow, int startCol, Ship ship, boolean isHorizontal) {
        int endRow = startRow;
        int endCol = startCol;

        if (isHorizontal) {
            endCol = startCol + ship.getLength() - 1;
        } else {
            endRow = startRow + ship.getLength() - 1;
        }

        if (isPlacementValid(startRow, startCol, endRow, endCol, ship)) {
            if (isHorizontal) {
                for (int col = startCol; col <= endCol; col++) {
                    board[startRow][col] = ship.symbol;
                    ship.setCurrentPos(startRow, col);
                }
            } else {
                for (int row = startRow; row <= endRow; row++) {
                    board[row][startCol] = ship.symbol;
                    ship.setCurrentPos(row, startCol);
                }
            }
            return true;
        }

        return false;
    }

    private boolean isPlacementValid(int startRow, int startCol, int endRow, int endCol, Ship ship) {
        if (endRow >= size || endCol >= size) {
            return false;
        }
        if (startRow == endRow) { // Horizontal placement
            for (int col = startCol; col <= endCol; col++) {
                if (!board[startRow][col].equals("~~")) {
                    return false;
                }
            }
        } else if (startCol == endCol) { // Vertical placement
            for (int row = startRow; row <= endRow; row++) {
                if (!board[row][startCol].equals("~~")) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public String takeShot(int row, int col) {
        if (board[row][col].equals("~~")) {
            missed.add(row + "," + col);
            board[row][col] = "oo";
            return "Miss!";
        } else if (board[row][col].contains("XX") || board[row][col].contains("oo")) {
            return "Already shot here!";
        } else {
            for (Ship ship : shipList) {
                for (ArrayList<Integer> pos : ship.getCurrentPos()) {
                    if (pos.get(0) == row && pos.get(1) == col) {
                        ship.hit(row, col);
                        board[row][col] = "XX";
                        return ship.isSunken() ? "Hit and sunk!" : "Hit!";
                    }
                }
            }
        }
        return "Error";
    }

    public ArrayList<Ship> createShips() {
        ArrayList<Ship> list = new ArrayList<>();
        // Carrier
        list.add(new Ship(5, "C1", "Carrier"));
        // Battleships
        list.add(new Ship(4, "B1", "Battle Ship"));
        list.add(new Ship(4, "B2", "Battle Ship"));
        // Submarine
        list.add(new Ship(3, "S1","Submarine"));
        list.add(new Ship(3, "S2","Submarine"));
        list.add(new Ship(3, "S3","Submarine"));
        // Destroyer
        list.add(new Ship(2, "D1","Destroyer"));
        list.add(new Ship(2, "D2","Destroyer"));
        list.add(new Ship(2, "D3","Destroyer"));
        list.add(new Ship(2, "D4","Destroyer"));
        return list;
    }

    public ArrayList<Ship> getShipList() { return shipList; }

    public void removeShip(Ship ship){
        shipList.remove(ship);
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
        System.out.println();
    }
}
