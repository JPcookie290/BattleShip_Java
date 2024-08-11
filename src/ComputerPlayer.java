import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    private ArrayList<ArrayList<Integer>> shotsTaken;
    private Random random;
    private Ship lastSunkenShip;

    public ComputerPlayer(String name, Gameboard gameboard, Gameboard enemyBoard) {
        super(name, gameboard, enemyBoard);
        this.shotsTaken = new ArrayList<>();
        this.random = new Random();
    }

    public void takeRandomShot() {
        int row, col;
        ArrayList<Integer> shot;

        do {
            row = random.nextInt(gameboard.getSize());
            col = random.nextInt(gameboard.getSize());
            shot = new ArrayList<>();
            shot.add(row);
            shot.add(col);
        } while (shotsTaken.contains(shot));

        shotsTaken.add(shot);
        String result = takeShot(row, col);
        System.out.println("Computer shot at (" + row + ", " + col + "): " + result);

        if (result.equals("Hit and sunk!")) {
            lastSunkenShip = getLastSunkenShip();
        }
    }

    public Ship getLastSunkenShip() {
        return lastSunkenShip;
    }

    public void placeShipsRandom(){
        int row, col;
        boolean orientation;
        ArrayList<Ship> shipList = gameboard.getShipList();
        for (Ship ship : shipList){
            do {
                row = random.nextInt(gameboard.getSize());
                col = random.nextInt(gameboard.getSize());
                orientation = random.nextInt(10) >= 5;
            } while (!gameboard.placeShip(row, col, ship, orientation));
        }
    }
}
