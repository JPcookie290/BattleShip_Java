import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player {
    private ArrayList<ArrayList<Integer>> shotsTaken;
    private Random random;
    private ArrayList<Integer> previousHitShip;
    private ArrayList<Integer> firstHitShip;
    private boolean foundShip;

    public ComputerPlayer(String name, Gameboard gameboard, Gameboard enemyBoard) {
        super(name, gameboard, enemyBoard);
        this.shotsTaken = new ArrayList<>();
        this.random = new Random();
        this.foundShip = false;
    }

    public void setFoundShip(boolean foundShip) {
        if (!foundShip){
            previousHitShip = new ArrayList<>();
        }
        this.foundShip = foundShip;
    }

    public boolean getFoundShip(){
        return foundShip;
    }

    public void setPreviousHitShip(){
        previousHitShip = shotsTaken.get(shotsTaken.size()-1);
    }

    public void setFirstHitShip(){
        firstHitShip = shotsTaken.getLast();
    }

    public String takeRandomShot() {
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

        return takeShot(row, col);
    }

    public String takeCalculatedShot() {
        int row = previousHitShip.get(0);
        int col = previousHitShip.get(1);
        int possibleShotsTaken = 0;
        ArrayList<Integer> shot = new ArrayList<>();
        ArrayList<ArrayList<Integer>> possibleShots = getPossibleShots(row,col);

        for (ArrayList<Integer> possibleShot : possibleShots){
            if (!shotsTaken.contains(possibleShot)){
                shot = possibleShot;
            } else {
                possibleShotsTaken++;
            }
        }

        if (possibleShotsTaken == possibleShots.size()){
            ArrayList<ArrayList<Integer>> firstPossibleShots = getPossibleShots(firstHitShip.get(0),firstHitShip.get(1));
            for (ArrayList<Integer> possibleShot : firstPossibleShots){
                if (!shotsTaken.contains(possibleShot)){
                    shot = possibleShot;
                }
            }
        }

        shotsTaken.add(shot);

        return takeShot(shot.get(0), shot.get(1));
    }

    public ArrayList<ArrayList<Integer>> getPossibleShots(int row, int col){
        ArrayList<ArrayList<Integer>> possibleShots = new ArrayList<>();

        if (row < 10){
            ArrayList<Integer> shot1 = new ArrayList<>();
            shot1.add(row + 1);
            shot1.add(col);
            possibleShots.add(shot1);
        }
        if (row > 0){
            ArrayList<Integer> shot2 = new ArrayList<>();
            shot2.add(row - 1);
            shot2.add(col);
            possibleShots.add(shot2);
        }
        if (col < 10){
            ArrayList<Integer> shot3 = new ArrayList<>();
            shot3.add(row);
            shot3.add(col + 1);
            possibleShots.add(shot3);

        }
        if (col > 0){
            ArrayList<Integer> shot4 = new ArrayList<>();
            shot4.add(row);
            shot4.add(col - 1);
            possibleShots.add(shot4);

        }

        return possibleShots;
    }

}
