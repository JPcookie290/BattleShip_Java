public class Main {
    public static void main(String[] args) {
        Gameboard pb = new Gameboard(10);
        Gameboard cb = new Gameboard(10);
        Player player = new Player("test", pb, cb);
        ComputerPlayer pc = new ComputerPlayer("pc", cb, pb);


    }
}