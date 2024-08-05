public class Player {
    protected String name;
    protected Gameboard gameboard;

    public Player(String name, Gameboard gameboard) {
        this.name = name;
        this.gameboard = gameboard;
    }

    public String getName() {
        return name;
    }

    public Gameboard getGameboard() {
        return gameboard;
    }

    public String takeShot(String position) {
        return gameboard.takeShot(position);
    }
}
