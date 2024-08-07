import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameboardUI {

    private static Gameboard playerBoard;
    private static Gameboard computerBoard;
    private static Player player;
    private static ComputerPlayer computer;
    private static boolean isPlacingShips = true;
    private static Ship currentShip;
    private static int shipIndex = 0;

    // Method to create a 10x10 grid with panels
    public static JPanel createGrid(JPanel[][] panels) {
        JPanel grid = new JPanel();
        grid.setPreferredSize(new Dimension(300, 300)); // Set the preferred size of the panel
        grid.setLayout(new GridLayout(10, 10)); // Set layout as 10x10 grid

        // Adding 100 panels to the grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JPanel square = new JPanel();
                square.setBackground(Color.BLUE);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panels[i][j] = square;
                grid.add(square);
            }
        }
        return grid;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Battle Ships"); // Create a new JFrame with title "Battle Ships"
        frame.setSize(900, 500); // Set the size of the frame
        frame.setLayout(new GridLayout(1, 3, 10, 10)); // Set layout as 1x3 grid with gaps
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        playerBoard = new Gameboard(10);
        computerBoard = new Gameboard(10);
        player = new Player("Player", playerBoard, computerBoard);
        computer = new ComputerPlayer("Computer", computerBoard, playerBoard);

        computer.placeShipsRandom();

        JPanel[][] playerPanels = new JPanel[10][10];
        JPanel[][] computerPanels = new JPanel[10][10];

        JPanel board1 = createGrid(playerPanels); // Create the first gameboard
        JPanel board2 = createGrid(computerPanels); // Create the second gameboard

        board1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margin to the first board
        board2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add margin to the second board

        JCheckBox horizontalCheckBox = new JCheckBox("Horizontal");
        horizontalCheckBox.setSelected(true);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(horizontalCheckBox, BorderLayout.NORTH);

        JButton placeShipsButton = new JButton("Place Ships");
        controlPanel.add(placeShipsButton, BorderLayout.SOUTH);

        placeShipsButton.addActionListener(e -> {
            if (isPlacingShips) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        int finalI = i;
                        int finalJ = j;
                        playerPanels[i][j].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                boolean isHorizontal = horizontalCheckBox.isSelected();
                                currentShip = playerBoard.getShipList().get(shipIndex);
                                if (playerBoard.placeShip(finalI, finalJ, currentShip, isHorizontal)) {
                                    for (ArrayList<Integer> pos : currentShip.getCurrentPos()) {
                                        playerPanels[pos.get(0)][pos.get(1)].setBackground(Color.GRAY);
                                    }
                                    shipIndex++;
                                    if (shipIndex >= playerBoard.getShipList().size()) {
                                        isPlacingShips = false;
                                        placeShipsButton.setText("Start Game");
                                        placeShipsButton.setEnabled(false);
                                        enableComputerBoard(computerPanels, playerPanels, frame);
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });

        frame.add(board1); // Add the first gameboard to the frame
        frame.add(controlPanel); // Add the control panel to the frame
        frame.add(board2); // Add the second gameboard to the frame

        frame.setVisible(true); // Make the frame visible
    }

    private static void enableComputerBoard(JPanel[][] computerPanels, JPanel[][] playerPanels, JFrame frame) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                computerPanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String result = player.takeShot(finalI, finalJ);
                        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
                            computerPanels[finalI][finalJ].setBackground(Color.RED);
                        } else if (result.equals("Miss!")) {
                            computerPanels[finalI][finalJ].setBackground(Color.WHITE);
                        }
                        computer.takeRandomShot();
                        updatePlayerBoard(playerPanels);
                        if (player.checkWon()) {
                            JOptionPane.showMessageDialog(frame, "Player won!");
                            frame.dispose();
                        } else if (computer.checkWon()) {
                            JOptionPane.showMessageDialog(frame, "Computer won!");
                            frame.dispose();
                        }
                    }
                });
            }
        }
    }

    private static void updatePlayerBoard(JPanel[][] playerPanels) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerBoard.board[i][j].equals("XX")) {
                    playerPanels[i][j].setBackground(Color.RED);
                } else if (playerBoard.board[i][j].equals("oo")) {
                    playerPanels[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
}
