public class Main {
    public static void main(String[] args) {
        Gameboard playerBoard = new Gameboard(10);
        Gameboard computerBoard = new Gameboard(10);

        Player player = new Player("Player", playerBoard, computerBoard);
        ComputerPlayer computer = new ComputerPlayer("Computer", computerBoard, playerBoard);

        computer.placeShipsRandom();

        playerBoard.placeShip(1, 9, playerBoard.getShipList().get(0), false);
        playerBoard.placeShip(0, 1, playerBoard.getShipList().get(1), true);
        playerBoard.placeShip(6, 5, playerBoard.getShipList().get(2), false);
        playerBoard.placeShip(6, 0, playerBoard.getShipList().get(3), false);
        playerBoard.placeShip(4, 2, playerBoard.getShipList().get(4), true);
        playerBoard.placeShip(0, 7, playerBoard.getShipList().get(5), false);
        playerBoard.placeShip(9, 8, playerBoard.getShipList().get(6), true);
        playerBoard.placeShip(7, 9, playerBoard.getShipList().get(7), false);
        playerBoard.placeShip(2, 0, playerBoard.getShipList().get(8), false);
        playerBoard.placeShip(8, 3, playerBoard.getShipList().get(9), true);

        //----------------- Test Information -----------------//

        // Player Shots
        System.out.println(player.takeShot(0, 1));
        System.out.println(player.takeShot(3, 2));
        System.out.println(player.takeShot(0, 3));
        System.out.println(player.takeShot(9, 4));
        System.out.println(player.takeShot(7, 5));

        // Computer Shots
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();
        computer.takeRandomShot();


        // Current Output
        System.out.println("Player:");
        playerBoard.printBoard();


        System.out.println("Computer:");
        computerBoard.printBoard();

    }
}