import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {
    private ArrayList<ArrayList<Integer>> shotsTaken;
    private Random random;


    public ComputerPlayer(String name, Gameboard gameboard, Gameboard enemyBoard) {
        super(name, gameboard, enemyBoard);
        this.shotsTaken = new ArrayList<>();
        this.random = new Random();
        //this.availableSots = getAvailableShots();
    }

    //public ArrayList<String> getAvailableShots(){};

    public void takeRandomShot() {
        Random rand = new Random();
        int randomCol = rand.nextInt(10);
        int randomRow = random.nextInt(10);
        ArrayList<Integer> randomShot = new ArrayList<Integer>();
        randomShot.add(randomCol);
        randomShot.add(randomRow);

        if (shotsTaken.contains(randomShot)){
            takeRandomShot();
        } else {
            shotsTaken.add(randomShot);
        }

        //System.out.println("col: " + randomCol + " row: " + randomRow);
        if ( enemyBoard.board[randomCol][randomRow].contains("~~")) {
            enemyBoard.board[randomCol][randomRow] = "oo";
        } else {
            enemyBoard.board[randomCol][randomRow] = "XX";
        }
    }

    // public void placeShips(){...}

    // Test static/no random
    public void placeShipsTest(){
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
        pc.placeShipsTest();

        // place player ships
        player.gameboard.placeShip("B10", "F10", player.gameboard.shipList.get(0));
        player.gameboard.placeShip("A2", "A5", player.gameboard.shipList.get(1));
        player.gameboard.placeShip("G6", "J6", player.gameboard.shipList.get(2));
        player.gameboard.placeShip("G1", "I1", player.gameboard.shipList.get(3));
        player.gameboard.placeShip("E3", "E5", player.gameboard.shipList.get(4));
        player.gameboard.placeShip("A8", "C8", player.gameboard.shipList.get(5));
        player.gameboard.placeShip("J9", "J10", player.gameboard.shipList.get(6));
        player.gameboard.placeShip("H10", "I10", player.gameboard.shipList.get(7));
        player.gameboard.placeShip("C1", "D1", player.gameboard.shipList.get(8));
        player.gameboard.placeShip("I4", "I5", player.gameboard.shipList.get(9));

        //----------------- test information's -----------------//

        // player takes shots
        player.takeShot("A1");
        player.takeShot("A4");
        player.takeShot("A5");
        player.takeShot("B4");
        player.takeShot("A3");

        // random shots Computer
        pc.takeRandomShot();
        pc.takeRandomShot();
        pc.takeRandomShot();
        pc.takeRandomShot();
        pc.takeRandomShot();

        //----------------- Current Output -----------------//
        // print computer board
        System.out.println("Computer Ships:");
        //pc.gameboard.printBoard();

        // print computer board
        System.out.println("Player Ships:");
        //player.gameboard.printBoard();

    }
}
