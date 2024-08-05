public class Main {
    public static void main(String[] args) {
        Gameboard pb = new Gameboard(10);
        Gameboard cb = new Gameboard(10);
        Player player = new Player("test", pb, cb);
        ComputerPlayer pc = new ComputerPlayer("pc", cb, pb);
        pc.placeShipsTest();

        player.takeShot("A1");
        player.takeShot("A4");
        player.takeShot("A5");
        player.takeShot("B4");
        player.takeShot("A3");


        // Test

        //System.out.println(cb.shipList.size());
        //System.out.println(pc.gameboard.getMissedShots());

    }
}