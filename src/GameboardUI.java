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
    private JLabel infoShipsRemainingComputer;
    private JLabel infoShipsRemainingPlayer;

    public GameboardUI(GameController controller) {
        this.controller = controller;
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Battle Ships");
        frame.setSize(950, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        playerPanels = new JPanel[10][10];
        computerPanels = new JPanel[10][10];

        JPanel board1 = createGrid(playerPanels);
        JPanel board2 = createGrid(computerPanels);

        board1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        horizontalCheckBox = new JCheckBox("Horizontal");
        horizontalCheckBox.setFont(new Font("Monospaced", Font.PLAIN, 16));
        horizontalCheckBox.setSelected(true);

        infoShipsRemainingComputer = new JLabel("The computer has 10 ships remaining.");
        infoShipsRemainingComputer.setFont(new Font("Monospaced", Font.PLAIN, 12));

        infoShipsRemainingPlayer = new JLabel("You have 10 ships remaining.");
        infoShipsRemainingPlayer.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(450,50));
        controlPanel.setLayout(new GridLayout(1,3));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));

        controlPanel.add(infoShipsRemainingPlayer);
        infoShipsRemainingPlayer.setVisible(false);
        controlPanel.add(horizontalCheckBox, BorderLayout.CENTER);
        controlPanel.add(infoShipsRemainingComputer);
        infoShipsRemainingComputer.setVisible(false);



        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout( new GridLayout(2,1));
        JLabel playerLabel = new JLabel("Player-Board:", SwingConstants.CENTER);
        playerLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JLabel computerLabel = new JLabel("Computer-Board:", SwingConstants.CENTER);
        computerLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));

        statusLabel = new JLabel("Place your ships on the board.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,2));
        infoPanel.add(playerLabel);
        infoPanel.add(computerLabel);

        notificationPanel.add(statusLabel);
        notificationPanel.add(infoPanel);

        //frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(notificationPanel, BorderLayout.NORTH);

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
                square.setBackground(new Color(147,186,240));
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panels[i][j] = square;
                grid.add(square);
            }
        }
        return grid;
    }

    public void updatePlayerShipPlacement(Ship ship) {
        for (ArrayList<Integer> pos : ship.getCurrentPos()) {
            playerPanels[pos.get(0)][pos.get(1)].setBackground(new Color(158, 158, 158));
        }
    }

    public void updateAfterPlayerShot(int row, int col, String result) {
        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            computerPanels[row][col].setBackground(new Color(255, 169, 53));
        } else if (result.equals("Miss!")) {
            computerPanels[row][col].setBackground(new Color(63, 110, 177));
        }
    }

    public void updateAfterComputerShot(Gameboard playerBoard) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (playerBoard.board[i][j].equals("XX")) {
                    playerPanels[i][j].setBackground(new Color(255, 169, 53));
                } else if (playerBoard.board[i][j].equals("oo")) {
                    playerPanels[i][j].setBackground(new Color(63, 110, 177));
                } else if (playerBoard.board[i][j].equals("SS")) {
                    playerPanels[i][j].setBackground(new Color(212, 53, 69));
                }
            }
        }
    }

    public void showSunkMessage(Ship sunkenShip, String whoSunk) {
        if (sunkenShip != null) {
            statusLabel.setText(whoSunk + " sunk a " + sunkenShip.title + "!");
            for (ArrayList<Integer> pos : sunkenShip.getCurrentPos()) {
                if (whoSunk.equals("Player")) {
                    computerPanels[pos.get(0)][pos.get(1)].setBackground(new Color(212, 53, 69));
                } else {
                    System.out.println("Computer sunk a Ship");
                }
            }
        }
    }

    public void updateInfoControl(ArrayList<Ship> ships, String shipOwner){
        if (shipOwner.contains("Computer")){
            infoShipsRemainingComputer.setText("The Computer has " + ships.size() + " ships remaining.");
        } else if (shipOwner.equals("Player")) {
            infoShipsRemainingPlayer.setText("You have " + ships.size() + " ships remaining.");
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
        controller = new GameController(); // new Game Controller
        frame.dispose();
    }

    public void startGame() {
        updateStatusLabel("All ships placed. Game started!");
        horizontalCheckBox.setVisible(false);
        infoShipsRemainingPlayer.setVisible(true);
        infoShipsRemainingComputer.setVisible(true);
    }

    public void updateStatusLabel(String message) {
        statusLabel.setText(message);
    }

    public boolean isPlacingShips() {
        return controller.isPlacingShips();
    }
}
