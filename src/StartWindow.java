import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {

    private JFrame startFrame;
    private JTextField playerNameField;
    private JRadioButton easyMode;
    private JRadioButton normalMode;
    private String selectedGameMode = "Normal"; // Default to normal
    private GameController gameController;

    public StartWindow() {
        createStartWindow();
    }

    private void createStartWindow() {
        startFrame = new JFrame("Welcome to Battle Ships");
        startFrame.setSize(400, 200);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Input for player name
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Enter your name: "));
        playerNameField = new JTextField(10);
        namePanel.add(playerNameField);
        panel.add(namePanel);

        // Radio buttons for game mode selection
        JPanel modePanel = new JPanel();
        modePanel.add(new JLabel("Choose game mode: "));
        easyMode = new JRadioButton("Easy");
        normalMode = new JRadioButton("Normal", true); // Default selection

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(easyMode);
        modeGroup.add(normalMode);

        modePanel.add(easyMode);
        modePanel.add(normalMode);
        panel.add(modePanel);

        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        startFrame.add(panel, BorderLayout.CENTER);
        startFrame.add(startButton, BorderLayout.SOUTH);

        startFrame.setVisible(true);
    }

    private void startGame() {
        String playerName = playerNameField.getText();
        if (playerName.isEmpty()) {
            JOptionPane.showMessageDialog(startFrame, "Please enter a name.");
            return;
        }

        // Get the selected game mode
        selectedGameMode = easyMode.isSelected() ? "Easy" : "Normal";

        // Close the start window
        startFrame.dispose();

        // Launch the main game with player name and game mode
        gameController = new GameController(playerName, selectedGameMode);
        gameController.startGame();
    }
}
