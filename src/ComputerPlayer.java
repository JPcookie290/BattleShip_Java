import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
    private Set<String> attemptedShots;
    private Random random;
    private int boardSize;
    private ArrayList<String> availableShots;

    public ComputerPlayer(String name, Gameboard gameboard, Gameboard enemyBoard) {
        super(name, gameboard, enemyBoard);
        this.attemptedShots = new HashSet<>();
        this.random = new Random();
        this.boardSize = gameboard.getSize();
        //this.availableSots = getAvailableShots();
    }

    //public ArrayList<String> getAvailableShots(){};

    //public void takeRandomShot() {}

    // Test static/no random
    public void placeShips(){
        // Carrier
        gameboard.placeShip("J2", "J6", gameboard.shipList.get(0));
        // Battleships
        gameboard.placeShip("B1", "E1", gameboard.shipList.get(1));
        gameboard.placeShip("F7", "F10", gameboard.shipList.get(2));
        // Submarine
        gameboard.placeShip("A7", "A9", gameboard.shipList.get(3));
        gameboard.placeShip("C5", "E5", gameboard.shipList.get(4));
        gameboard.placeShip("H1", "H3", gameboard.shipList.get(5));
        // 	Destroyer
        gameboard.placeShip("I10", "J10", gameboard.shipList.get(6));
        gameboard.placeShip("J8", "J9", gameboard.shipList.get(7));
        gameboard.placeShip("A3", "A4", gameboard.shipList.get(8));
        gameboard.placeShip("D9", "E9", gameboard.shipList.get(9));

    }


    public static void main(String[] args) {
        // initialize game boards
        Gameboard pb = new Gameboard(10);
        Gameboard cb = new Gameboard(10);

        // initialize players
        Player player = new Player("test", pb, cb);
        ComputerPlayer pc = new ComputerPlayer("pc", cb, pb);

        // place computer ships for test
        pc.placeShips();

        // place player ships
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(0));
        player.gameboard.placeShip("A2", "A5", player.gameboard.shipList.get(1));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(2));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(3));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(4));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(5));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(6));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(7));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(8));
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(9));

        // player takes shots
        player.takeShot("A1");
        player.takeShot("A4");
        player.takeShot("A5");
        player.takeShot("B4");
        player.takeShot("A3");

        // print computer board
        System.out.println("Computer:");
        pc.gameboard.printBoard();
        // print computer board
        System.out.println("Player:");
        player.gameboard.printBoard();
        // other informations
        //System.out.println(cb.shipList.size());
        //System.out.println(pc.gameboard.getMissedShots());

    }
}
