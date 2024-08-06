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

    public void placeShipsTest() {
        gameboard.placeShip(9, 1, gameboard.shipList.get(0), true);
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

    public static void main(String[] args) {
        Gameboard playerBoard = new Gameboard(10);
        Gameboard computerBoard = new Gameboard(10);

        Player player = new Player("Player", playerBoard, computerBoard);
        ComputerPlayer computer = new ComputerPlayer("Computer", computerBoard, playerBoard);

        computer.placeShipsTest();

        playerBoard.placeShip(1, 9, playerBoard.shipList.get(0), false);
        playerBoard.placeShip(0, 1, playerBoard.shipList.get(1), true);
        playerBoard.placeShip(6, 5, playerBoard.shipList.get(2), false);
        playerBoard.placeShip(6, 0, playerBoard.shipList.get(3), false);
        playerBoard.placeShip(4, 2, playerBoard.shipList.get(4), true);
        playerBoard.placeShip(0, 7, playerBoard.shipList.get(5), true);
        playerBoard.placeShip(9, 9, playerBoard.shipList.get(6), false);
        playerBoard.placeShip(9, 7, playerBoard.shipList.get(7), true);
        playerBoard.placeShip(0, 2, playerBoard.shipList.get(8), true);
        playerBoard.placeShip(3, 8, playerBoard.shipList.get(9), false);

        //----------------- Test Informationen -----------------//

        // Spieler nimmt Schüsse
        System.out.println(player.takeShot(0, 1));
        System.out.println(player.takeShot(0, 2));
        System.out.println(player.takeShot(0, 3));
        System.out.println(player.takeShot(0, 4));
        System.out.println(player.takeShot(0, 5));

        // Zufällige Schüsse des Computers
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();

        // Aktuelle Ausgabe
        System.out.println("Spieler-Brett:");
        playerBoard.printBoard();

        System.out.println("Computer-Brett:");
        computerBoard.printBoard();
    }
}
