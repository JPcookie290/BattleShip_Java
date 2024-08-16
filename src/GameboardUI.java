import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameboardUI {

    private GameController controller;
    private JPanel[][] playerPanels;
    private JPanel[][] computerPanels;
    private JLabel statusLabel;
    private JPanel buttonView;
    private JPanel infoView;
    private JLabel infoShipsRemainingComputer;
    private JLabel infoShipsRemainingPlayer;
    private JPanel board1;

    /* ---------- initializes the game controller component ---------- */
    public GameboardUI(GameController controller) {
        this.controller = controller;
    }

    /* ---------- creates the UI ---------- */
    public void createAndShowGUI() {
        /* ---------- creates frame ---------- */
        JFrame frame = new JFrame("Battle Ships");
        frame.setSize(950, 600);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* ---------- initializes playerPanels and computerPanels  ---------- */
        playerPanels = new JPanel[10][10];
        computerPanels = new JPanel[10][10];

        /* ---------- creates the boards ---------- */
        board1 = createGrid(playerPanels);
        JPanel board2 = createGrid(computerPanels);

        board1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* ---------- Creates controlPanel, located beneath the gameboards ---------- */
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(450,50));
        controlPanel.setLayout(new GridLayout(2,1));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,0));

        /* ----------initializes buttonView and infoView---------- */
        buttonView = createButtonView();
        infoView = createInfoView();

        /* ---------- adds buttonView and infoView to the controlPanel ---------- */
        controlPanel.add(buttonView);
        controlPanel.add(infoView);
        infoView.setVisible(false);

        /* ---------- creates notificationPanel ---------- */
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout( new GridLayout(2,1));

        /* ---------- creates playerLabel and computerLabel ---------- */
        JLabel playerLabel = new JLabel("Player-Board:", SwingConstants.CENTER);
        playerLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JLabel computerLabel = new JLabel("Computer-Board:", SwingConstants.CENTER);
        computerLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));

        /* ---------- initialized statusLabel ---------- */
        statusLabel = new JLabel("Place your ships on the board.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

        /* ---------- create infoPanel and adds playerLabel and computerLabel ---------- */
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1,2));
        infoPanel.add(playerLabel);
        infoPanel.add(computerLabel);

        /* ---------- adds statusLabel and infoPanel to notificationPanel ---------- */
        notificationPanel.add(statusLabel);
        notificationPanel.add(infoPanel);

        /* ---------- adds notificationPanel to frame ---------- */
        frame.add(notificationPanel, BorderLayout.NORTH);

        /* ---------- creates mouseEvents for the Panels ---------- */
        // Player Panels
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int finalI = i;
                int finalJ = j;
                playerPanels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (controller.isPlacingShips()) {
                            if (e.getButton() == MouseEvent.BUTTON1){
                                //System.out.println("test left");
                                controller.placePlayerShip(finalI, finalJ, true);
                            }
                            if (e.getButton() == MouseEvent.BUTTON3){
                                //System.out.println("test right");
                                controller.placePlayerShip(finalI, finalJ, false);
                            }
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

        /* ---------- creates boardsPanel and adds board1 and board2 to it ---------- */
        JPanel boardsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        boardsPanel.add(board1);
        boardsPanel.add(board2);

        /* ---------- adds boardsPanel and controlPanel to frame ---------- */
        frame.add(boardsPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        /* ---------- sets frame visibility to true ---------- */
        frame.setVisible(true);
    }

    private @NotNull JButton getStartButton() {
        JButton startButton = new JButton("start game");
        startButton.setFont(new Font("Monospaced", Font.PLAIN, 16));

        /* ---------- adds action to the random placement button ---------- */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame();
            }
        });
        return startButton;
    }

    /* ---------- creates random placement button ---------- */
    private @NotNull JButton getRandomPlacement() {
        JButton randomPlacement = new JButton("randomize ship placement");
        randomPlacement.setFont(new Font("Monospaced", Font.PLAIN, 16));

        /* ---------- adds action to the random placement button ---------- */
        randomPlacement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.placeRandomPlayerShips();
            }
        });
        return randomPlacement;
    }

    /* ---------- creates reset button ---------- */
    private @NotNull JButton getResetButton() {
        JButton resetButton = new JButton("reset ships");
        resetButton.setFont(new Font("Monospaced", Font.PLAIN, 16));

        /* ---------- adds action to reset button ---------- */
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: UI implementation
                controller.resetShips();
            }
        });
        return resetButton;
    }

    private JPanel createButtonView(){
        JPanel view = new JPanel();
        view.setLayout(new GridLayout(1,3));

        // get buttons
        JButton resetButton = getResetButton();
        JButton randomPlacement = getRandomPlacement();
        JButton startButton = getStartButton();

        // add buttons
        view.add(resetButton);
        view.add(randomPlacement, BorderLayout.CENTER);
        view.add(startButton);

        return view;
    }

    private JPanel createInfoView(){
        JPanel view = new JPanel();
        view.setLayout(new GridLayout(1,2));

        // initialize labels
        infoShipsRemainingComputer = new JLabel("The computer has 10 ships remaining.");
        infoShipsRemainingComputer.setFont(new Font("Monospaced", Font.PLAIN, 12));
        infoShipsRemainingPlayer = new JLabel("You have 10 ships remaining.");
        infoShipsRemainingPlayer.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // add labels
        view.add(infoShipsRemainingPlayer);
        view.add(infoShipsRemainingComputer);

        return view;
    }

    /* ---------- creates basic grid layout for the game UI ---------- */
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

    /* ---------- update UI after ship placement of the player ---------- */
    public void updatePlayerShipPlacement(Ship ship) {
        for (ArrayList<Integer> pos : ship.getCurrentPos()) {
            playerPanels[pos.get(0)][pos.get(1)].setBackground(new Color(158, 158, 158));
        }
    }

    /* ---------- update UI after the player takes a shot ---------- */
    public void updateAfterPlayerShot(int row, int col, String result) {
        if (result.equals("Hit!") || result.equals("Hit and sunk!")) {
            computerPanels[row][col].setBackground(new Color(255, 169, 53));
        } else if (result.equals("Miss!")) {
            computerPanels[row][col].setBackground(new Color(63, 110, 177));
        }
    }

    /* ---------- update UI after computer takes a shot ---------- */
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

    /* ---------- creates a sunk message and updates the statusLabel ---------- */
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

    /* ---------- updates remaining ships info elements ---------- */
    public void updateInfoControl(ArrayList<Ship> ships, String shipOwner){
        if (shipOwner.contains("Computer")){
            infoShipsRemainingComputer.setText("The Computer has " + ships.size() + " ships remaining.");
        } else if (shipOwner.equals("Player")) {
            infoShipsRemainingPlayer.setText("You have " + ships.size() + " ships remaining.");
        }
    }

    /* ---------- creates end of game JOptionPane ---------- */
    public void showGameEndMessage(String message) {
        int response = JOptionPane.showConfirmDialog(null, message + " Would you like to play again?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            System.exit(0);
        }
    }

    /* ---------- restart game function ---------- */
    private void restartGame() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(statusLabel);
        frame.getContentPane().removeAll();
        controller = new GameController(); // new Game Controller
        frame.dispose();
    }

    /* ---------- start game function ---------- */
    public void startGame() {
        buttonView.setVisible(false);
        infoView.setVisible(true);
    }

    /* ---------- updates statusLabel ---------- */
    public void updateStatusLabel(String message) {
        statusLabel.setText(message);
    }
    
    public boolean isPlacingShips() {
        return controller.isPlacingShips();
    }
}

