import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    protected String name;
    protected Gameboard gameboard;
    public Gameboard enemyBoard;
    private Ship lastSunkenShip;
    private Random random;

    public Player(String name, Gameboard gameboard, Gameboard enemyBoard) {
        this.name = name;
        this.random = new Random();
        this.random = new Random();
        this.gameboard = gameboard;
        this.enemyBoard = enemyBoard;
        this.lastSunkenShip = null;
    }

    public String getName() {
        return name;
    }

    public Gameboard getGameboard() {
        return gameboard;
    }

    public String takeShot(int row, int col) {
        String result;
        // Check taken Shot
        if (row < 0 || col < 0 || row >= enemyBoard.getSize() || col >= enemyBoard.getSize()) {
            return  "Invalid";
        }
        result = enemyBoard.takeShot(row, col);

        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            for (Ship ship : enemyBoard.getShipList()) {
                if (ship.isSunken()) {
                    lastSunkenShip = ship;
                    for (ArrayList<Integer> pos : ship.getCurrentPos()) {
                        enemyBoard.board[pos.get(0)][pos.get(1)] = "SS";
                    }
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

    public void placeShipsRandom(){
        int row, col;
        boolean orientation;
        ArrayList<Ship> shipList = gameboard.getShipList();
        ArrayList<Ship> placedShips = gameboard.getPlacedShips();
        for (Ship ship : shipList){
            if (!placedShips.contains(ship)){
                do {
                    row = random.nextInt(gameboard.getSize());
                    col = random.nextInt(gameboard.getSize());
                    orientation = random.nextInt(10) >= 5;
                } while (!gameboard.placeShip(row, col, ship, orientation));
            }
        }
    }

    public boolean checkWon() {
        return enemyBoard.getShipList().isEmpty();
    }

    public Ship getLastSunkenShip() {
        return lastSunkenShip;
    }
}
