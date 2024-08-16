import java.util.ArrayList;


public class Gameboard {
    private final int size;
    private ArrayList<String> missed;
    public String[][] board;
    private ArrayList<Ship> shipList;
    private ArrayList<Ship> placedShips;

    public Gameboard(int size) {
        this.size = size;
        this.missed = new ArrayList<>();
        this.board = new String[size][size];
        this.shipList = createShips();
        this.placedShips = new ArrayList<>();
        createBoard();
    }

    /* ---------- creates the board ---------- */
    private void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "~~";
            }
        }
    }

    /* ---------- Function to place ship in the board ---------- */
    public boolean placeShip(int startRow, int startCol, Ship ship, boolean isHorizontal) {
        int endRow = startRow;
        int endCol = startCol;

        if (isHorizontal) {
            endCol = startCol + ship.getLength() - 1;
        } else {
            endRow = startRow + ship.getLength() - 1;
        }

        if (endRow >= size || endCol >= size) {
            return false;
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
            placedShips.add(ship);
            //shipList.remove(ship);
            return true;
        }

        return false;
    }

    /* ---------- Function validation for the placement of the ship ---------- */
    private boolean isPlacementValid(int startRow, int startCol, int endRow, int endCol, Ship ship) {
        if (startRow < 0 || startCol < 0 || endRow >= size || endCol >= size) {
            return false;
        }

        // Horizontal placement
        if (startRow == endRow) {
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

    /* ---------- take shot function ---------- */
    public String takeShot(int row, int col) {
        if (board[row][col].equals("~~")) {
            missed.add(row + "," + col);
            board[row][col] = "oo";
            return "Miss!";
        } else if (board[row][col].contains("XX") || board[row][col].contains("oo") || board[row][col].contains("SS")) {
            return "Invalid";
        } else {
            for (Ship ship : placedShips) {
                for (ArrayList<Integer> pos : ship.getCurrentPos()) {
                    if (pos.get(0) == row && pos.get(1) == col) {
                        ship.hit();
                        board[row][col] = "XX";
                        return ship.isSunken() ? "Hit and sunk!" : "Hit!";
                    }
                }
            }
        }
        return "Error";
    }

    /* ---------- create existing ships list ---------- */
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

    /* ---------- remove ship output ---------- */
    public void removeShip(Ship ship){
        shipList.remove(ship);
    }

    /* ---------- Getter functions ---------- */
    public int getSize() {
        return size;
    }

    public ArrayList<Ship> getPlacedShips(){ return placedShips; }

    public ArrayList<Ship> getShipList() { return shipList; }

    public ArrayList<String> getMissedShots() {
        return missed;
    }

    /* ---------- Function for terminal output ---------- */
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printShipInfo(Ship ship){
        System.out.println(ship.title);
        if (!ship.getCurrentPos().isEmpty()) {
            for (ArrayList<Integer> pos : ship.getCurrentPos()){
                System.out.print(pos);
            }
            System.out.println();
        } else {
            System.out.println("currentPos is empty.");
        }

    }

    public void printInfo(){
        System.out.println("Gameboard:");
        printBoard();
        System.out.println("shiplist:");
        for (Ship ship : shipList){
            printShipInfo(ship);
        }
        System.out.println("placedShips:");
        if (!placedShips.isEmpty()){
            for (Ship ship : placedShips){
                printShipInfo(ship);
            }
        } else {
            System.out.println("placedShips is empty!");
        }
    }
}



