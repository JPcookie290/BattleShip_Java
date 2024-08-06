import javax.swing.*;
import java.awt.*;

public class GameboardUI {
    public static JPanel createGrid(Color color){
        JPanel grid = new JPanel();
        grid.setSize(10,10);
        grid.setLayout( new GridLayout(10,10));
        for (int i = 0; i < 100; i++) {
            JButton square = new JButton();
            square.setBackground(color);
            grid.add(square);
        }
        return grid;
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame("Battle Ships");
        frame.setSize(900,500);

        frame.setLayout(new GridLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel board_1 = createGrid(Color.PINK);
        JPanel board_2 = createGrid(Color.ORANGE);


        frame.add(board_1);

        frame.add(board_2);
        frame.setVisible(true);
    }
}
