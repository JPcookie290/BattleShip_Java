public class GameController {
    private Gameboard playerBoard;
    private Gameboard computerBoard;
    private Player player;
    private ComputerPlayer computer;
    private GameboardUI gameboardUI;
    private int shipIndex = 0;
    private boolean isPlacingShips = true;

    public GameController() {
        this.playerBoard = new Gameboard(10);
        this.computerBoard = new Gameboard(10);
        this.player = new Player("Player", playerBoard, computerBoard);
        this.computer = new ComputerPlayer("Computer", computerBoard, playerBoard);
        this.gameboardUI = new GameboardUI(this);

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
            if (shipIndex >= playerBoard.getShipList().size()) {
                isPlacingShips = false;
                gameboardUI.startGame();
            } else {
                gameboardUI.updateStatusLabel("Place the next ship.");
            }
        } else {
            gameboardUI.updateStatusLabel("Cannot place ship here!");
        }
    }

    private void handlePlayerShot(int row, int col) {
        String result = player.takeShot(row, col);
        gameboardUI.updateAfterPlayerShot(row, col, result);

        if (result.equals("Hit and sunk!")) {
            Ship sunkenShip = player.getLastSunkenShip();
            gameboardUI.showSunkMessage(sunkenShip, "Player");
            //gameboardUI.changeColorSunkenShip(sunkenShip);
        }
        System.out.println(result);

        if (player.checkWon()) {
            gameboardUI.showGameEndMessage("Player won!");
        } else if (!result.contains("Invalid")){
            computerTakeShot();
        }
    }

    public void computerTakeShot() {
        computer.takeRandomShot();
        gameboardUI.updateAfterComputerShot(playerBoard);

        Ship sunkenShip = computer.getLastSunkenShip();
        if (sunkenShip != null) {
            gameboardUI.showSunkMessage(sunkenShip, "Computer");
        }

        if (computer.checkWon()) {
            gameboardUI.showGameEndMessage("Computer won!");
        }
    }

    public Gameboard getPlayerBoard() {
        return playerBoard;
    }
}
