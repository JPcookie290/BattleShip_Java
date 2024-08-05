import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
    private Set<String> attemptedShots;
    private Random random;
    private int boardSize;

    public ComputerPlayer(String name, Gameboard gameboard) {
        super(name, gameboard);
        this.attemptedShots = new HashSet<>();
        this.random = new Random();
        this.boardSize = gameboard.getSize();
    }

    public String takeRandomShot() {
        String position;
        do {
            position = generateRandomPosition();
        } while (attemptedShots.contains(position));
        attemptedShots.add(position);
        return takeShot(position);
    }

    private String generateRandomPosition() {
        char row = (char) ('A' + random.nextInt(boardSize));
        int col = random.nextInt(boardSize) + 1;
        return "" + row + col;
    }
}
