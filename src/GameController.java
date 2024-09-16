
import java.util.ArrayList;


public class GameController {
    private Gameboard playerBoard;
    private Gameboard computerBoard;
    private Player player;
    private ComputerPlayer computer;
    private GameboardUI gameboardUI;
    private int shipIndex = 0;
    private boolean isPlacingShips = true;
    private String gameMode;  // Added game mode
    private String playerName;

    public GameController(String playerName, String gameMode) {
        this.playerBoard = new Gameboard(10);
        this.computerBoard = new Gameboard(10);
        this.player = new Player(playerName, playerBoard, computerBoard);
        this.computer = new ComputerPlayer("Computer", computerBoard, playerBoard);
        this.gameboardUI = new GameboardUI(this);

        if (gameMode.equals("Easy")){
            computer.setEasyMode(true);
        }

        this.computer.placeShipsRandom();
        this.gameboardUI.createAndShowGUI();
    }

    public boolean isPlacingShips() {
        return isPlacingShips;
    }

    public void playerClickOnComputerBoard(int row, int col) {
        if (!isPlacingShips) {
            handlePlayerShot(row, col);
        }
    }

    public void placePlayerShip(int row, int col, boolean isHorizontal) {
        Ship currentShip = playerBoard.getShipList().get(shipIndex);
        if (playerBoard.placeShip(row, col, currentShip, isHorizontal)) {
            gameboardUI.updatePlayerShipPlacement(currentShip);
            shipIndex++;
            if (playerBoard.getPlacedShips().size() >= playerBoard.getShipList().size()) {
                isPlacingShips = false;
                gameboardUI.updateStatusLabel("All ships have been placed! Click the start button.");
            } else {
                gameboardUI.updateStatusLabel("Place the next ship.");
            }
        } else {
            gameboardUI.updateStatusLabel("Cannot place ship here!");
        }
    }

    public void placeRandomPlayerShips(){
        player.placeShipsRandom();
        ArrayList<Ship> currentShips = playerBoard.getShipList();
        for (Ship ship : currentShips) {
            gameboardUI.updatePlayerShipPlacement(ship);
        }
        isPlacingShips = false;
        gameboardUI.updateStatusLabel("All ships have been placed! Click the start button.");
    }

    private void handlePlayerShot(int row, int col) {
        String result = player.takeShot(row, col);
        gameboardUI.updateAfterPlayerShot(row, col, result);

        if (result.equals("Hit and sunk!")) {
            Ship sunkenShip = player.getLastSunkenShip();
            gameboardUI.showSunkMessage(sunkenShip, "Player");
            gameboardUI.updateInfoControl(computerBoard.getShipList(), "Computer");
        } else if (result.equals("Hit!")) {
            gameboardUI.updateStatusLabel("Hit a ship!");
        } else {
            gameboardUI.updateStatusLabel(" ");
        }

        if (player.checkWon()) {
            gameboardUI.showGameEndMessage("Player won!");
        } else if (result.contains("Invalid")){
            gameboardUI.updateStatusLabel("Already shot here!");
        } else {
            computerTakeShot();
        }
    }

    public void computerTakeShot() {
        String result;

        if (!computer.getFoundShip()){
            result = computer.takeRandomShot();
        } else if (computer.isEasyMode()){
            result = computer.takeRandomShot();
        } else {
            result = computer.takeCalculatedShot();
        }

        gameboardUI.updateAfterComputerShot(playerBoard);

        if (result.equals("Hit and sunk!")) {
            computer.setFoundShip(false);
            Ship sunkenShip = computer.getLastSunkenShip();
            gameboardUI.showSunkMessage(sunkenShip, "Computer");
            gameboardUI.updateInfoControl(playerBoard.getShipList(), "Player");
        }

        if (result.equals("Hit!")){
            if (!computer.getFoundShip()){
                computer.setFirstHitShip();
                computer.setFoundShip(true);
            }
            computer.setPreviousHitShip();
        }


        if (computer.checkWon()) {
            gameboardUI.showGameEndMessage("Computer won!");
        }
    }

    public Gameboard getPlayerBoard() {
        return playerBoard;
    }

    public void startGame(){
        if (isPlacingShips()){
            gameboardUI.updateStatusLabel("Not all ships have been placed!");
        } else {
            gameboardUI.startGame();;
        }
    }


    public void resetShips() {
        playerBoard.resetBoard();

        gameboardUI.resetGameboard();

        isPlacingShips = true;
        shipIndex = 0;
        gameboardUI.updateStatusLabel("Place your ships on the board.");
    }

    public String getPlayerName() {return playerName;}

    public String getGameMode() {return gameMode;}
}

