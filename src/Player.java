public class Player {
    protected String name;
    protected Gameboard gameboard;
    public Gameboard enemyBoard;

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
        String result = enemyBoard.takeShot(row, col);
        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            for (Ship ship : enemyBoard.shipList) {
                if (ship.isSunken()) {
                    enemyBoard.shipList.remove(ship);
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkWon() {
        return enemyBoard.shipList.isEmpty();
    }
}
