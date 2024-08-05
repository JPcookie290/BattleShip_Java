import java.util.ArrayList;

public class Player {
    protected String name;
    protected Gameboard gameboard;
    public  Gameboard enemyBoard;


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


    public String takeShot(String position) {
        Ship ship = enemyBoard.takeShot(position);

        if (ship != null) {
            if (ship.isSunken()){
                enemyBoard.shipList.remove(ship);
                checkWon();
                return "Sunken";
            }
            return "Hit";
        } else {
            return "Miss";
        }
    }

    public boolean checkWon(){
        if (enemyBoard.shipList.isEmpty()){
            return true;
        };
        return false;
    }
}
