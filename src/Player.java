public class Player {
    protected String name;
    protected Gameboard gameboard;
    public Gameboard enemyBoard;
    private Ship lastSunkenShip;

    public Player(String name, Gameboard gameboard, Gameboard enemyBoard) {
        this.name = name;
        this.gameboard = gameboard;
        this.enemyBoard = enemyBoard;
    }

    public String getName() {
        return name;
    }

    public Gameboard getGameboard() {
        return gameboard;
    }

    public String takeShot(int row, int col) {
        // Check taken Shot
        if (row < 0 || col < 0 || row >= enemyBoard.getSize() || col >= enemyBoard.getSize()) {
            return "Invalid shot";
        }

        String result = enemyBoard.takeShot(row, col);

        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            for (Ship ship : enemyBoard.getShipList()) {
                if (ship.isSunken()) {
                    lastSunkenShip = ship;
                    enemyBoard.removeShip(ship);

                    if (checkWon()) {
                        System.out.println(name + " has won!");
                        return "Won";
                    }
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkWon() {
        return enemyBoard.getShipList().isEmpty();
    }

    public Ship getLastSunkenShip() {
        return lastSunkenShip;
    }
}
