import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameboardUI {

    private GameController controller;
    private JPanel[][] playerPanels;
    private JPanel[][] computerPanels;
    private JLabel statusLabel;
    private JCheckBox horizontalCheckBox;

    public GameboardUI(GameController controller) {
        this.controller = controller;
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Battle Ships");
        frame.setSize(900, 500);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerPanels = new JPanel[10][10];
        computerPanels = new JPanel[10][10];

        JPanel board1 = createGrid(playerPanels);
        JPanel board2 = createGrid(computerPanels);

        board1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        horizontalCheckBox = new JCheckBox("Horizontal");
        horizontalCheckBox.setSelected(true);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(horizontalCheckBox, BorderLayout.NORTH);

        statusLabel = new JLabel("Place your ships on the board.", SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.NORTH);

        // Player Panels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                playerPanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (controller.isPlacingShips()) {
                            boolean isHorizontal = horizontalCheckBox.isSelected();
                            controller.placePlayerShip(finalI, finalJ, isHorizontal);
                        }
                    }
                });
            }
        }

        // Computer Panels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                computerPanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        controller.playerClickOnComputerBoard(finalI, finalJ);
                    }
                });
            }
        }

        JPanel boardsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        boardsPanel.add(board1);
        boardsPanel.add(board2);

        frame.add(boardsPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public JPanel createGrid(JPanel[][] panels) {
        JPanel grid = new JPanel();
        grid.setPreferredSize(new Dimension(300, 300));
        grid.setLayout(new GridLayout(10, 10));

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

    public void updatePlayerShipPlacement(Ship ship) {
        for (ArrayList<Integer> pos : ship.getCurrentPos()) {
            playerPanels[pos.get(0)][pos.get(1)].setBackground(Color.GRAY);
        }
    }

    public void updateAfterPlayerShot(int row, int col, String result) {
        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            computerPanels[row][col].setBackground(Color.RED);
        } else if (result.equals("Miss!")) {
            computerPanels[row][col].setBackground(Color.WHITE);
        }
    }

    public void updateAfterComputerShot(Gameboard playerBoard) {
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

    public void showSunkMessage(Ship sunkenShip, String whoSunk) {
        if (sunkenShip != null) {
            statusLabel.setText(whoSunk + " sunk a " + sunkenShip.title + "!");
            for (ArrayList<Integer> pos : sunkenShip.getCurrentPos()) {
                if (whoSunk.equals("Player")) {
                    computerPanels[pos.get(0)][pos.get(1)].setBackground(Color.BLACK);
                } else {
                    playerPanels[pos.get(0)][pos.get(1)].setBackground(Color.BLACK);
                }
            }
        }
    }

    public void showGameEndMessage(String message) {
        int response = JOptionPane.showConfirmDialog(null, message + " Would you like to play again?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    private void restartGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(statusLabel);
        frame.getContentPane().removeAll();
        controller = new GameController(); // new Game COntroller
        frame.dispose();
    }

    public void startGame() {
        updateStatusLabel("All ships placed. Game started!");
    }

    public void updateStatusLabel(String message) {
        statusLabel.setText(message);
    }

    public boolean isPlacingShips() {
        return controller.isPlacingShips();
    }
}
