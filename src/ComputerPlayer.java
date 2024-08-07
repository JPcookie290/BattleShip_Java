import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    private ArrayList<ArrayList<Integer>> shotsTaken;
    private Random random;

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
    }

    /*    public void placeShipsTest() {
          /*gameboard.placeShip(9, 1, gameboard.shipList.get(0), true);
            gameboard.placeShip(1, 0, gameboard.shipList.get(1), false);
            gameboard.placeShip(5, 6, gameboard.shipList.get(2), true);
            gameboard.placeShip(0, 6, gameboard.shipList.get(3), true);
            gameboard.placeShip(2, 4, gameboard.shipList.get(4), false);
            gameboard.placeShip(7, 0, gameboard.shipList.get(5), true);
            gameboard.placeShip(8, 9, gameboard.shipList.get(6), false);
            gameboard.placeShip(9, 7, gameboard.shipList.get(7), true);
            gameboard.placeShip(0, 2, gameboard.shipList.get(8), true);
            gameboard.placeShip(3, 8, gameboard.shipList.get(9), false);
        }
    */

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
