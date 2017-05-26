package game;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.ModelInterface;

public class ViewGame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    //private ModelInterface mi;
    private ModelGame mg;
    private JPanel gamePanel;
    private JPanel[][] gameGrid;
    private JPanel utilityPanel;
    private JButton undoButton;
    private JButton remakeButton;
    private JLabel timerLabel;
    private JButton soundButton;
    private JButton quitButton;
    private JButton saveButton;

    private JButton pause_resumeButton;
    private JLabel TimerHeader;

    public ViewGame(ModelInterface mi, ModelGame mg) {

        //this.mi = mi;
        this.mg = mg;

        this.setSize(mi.getDimensions());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new FlowLayout());
        //this.setLayout(new GridBagLayout());
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        initialiseGame();

    }

    private void initialiseGame() {

        setupGamePanel();
        setupUtilityPanel();

    }

    private void setupGamePanel() {

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(mg.getYSizeOfBoard(), mg.getXSizeOfBoard()));

        gameGrid = new JPanel[mg.getYSizeOfBoard()][mg.getXSizeOfBoard()];

        for (int y = 0; y < mg.getYSizeOfBoard(); y++) {
            for (int x = 0; x < mg.getXSizeOfBoard(); x++) {
                gameGrid[y][x] = new JPanel();
                setPanelAppearance(gameGrid[y][x], x, y);
                gamePanel.add(gameGrid[y][x]);
            }
        }

        //gamePanel.setBounds(0, 0, 1080, 720);
        gamePanel.setPreferredSize(new Dimension(1080, 720));

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        this.add(gamePanel, BorderLayout.LINE_START);

    }

    private void setPanelAppearance(JPanel tilePanel, int i, int j) {

        if (mg.getSobokanBoardAtXY(i, j) == ("bg")) {
            tilePanel.setBackground(Color.GREEN);
        } else if (mg.getSobokanBoardAtXY(i, j) == ("pg")) {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i, j) == ("w")) {
            tilePanel.setBackground(Color.BLACK);
        } else if (mg.getSobokanBoardAtXY(i, j) == ("p")) {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i, j) == ("b")) {
            tilePanel.setBackground(Color.YELLOW);
        } else if (mg.getSobokanBoardAtXY(i, j) == ("g")) {
            tilePanel.setBackground(Color.BLUE);
            // for debugging purposes, can comment out later
        } else if (mg.getSobokanBoardAtXY(i, j) == "x") {
            tilePanel.setBackground(Color.PINK);
        } else {
            tilePanel.setBackground(Color.WHITE);
        }
        //System.out.print(mg.getSobokanBoardAtXY(i, j));
        //System.out.print(tilePanel.getBackground());

    }

    private void setupUtilityPanel() {

        utilityPanel = new JPanel();
        utilityPanel.setBackground(Color.PINK);
        utilityPanel.setLayout(null);
        //utilityPanel.setBounds(1080, 0, 200, 720);
        utilityPanel.setPreferredSize(new Dimension(200, 720));
        this.add(utilityPanel, BorderLayout.LINE_END);

        TimerHeader = new JLabel("Time:", JLabel.LEFT);
        TimerHeader.setBounds(50, 130, 120, 30);
        TimerHeader.setFont(new Font("Default", Font.PLAIN, 20));
        utilityPanel.add(TimerHeader);

        timerLabel = new JLabel("", JLabel.CENTER);
        timerLabel.setBounds(50, 150, 120, 30);
        timerLabel.setFont(new Font("Default", Font.PLAIN, 20));
        utilityPanel.add(timerLabel);

        pause_resumeButton = new JButton("Pause");
        pause_resumeButton.setBounds(50, 200, 120, 50);
        utilityPanel.add(pause_resumeButton);
        
        /*TimerPanel t = new TimerPanel();
        t.setBounds(10, 150, 200, 100);
        utilityPanel.add(t);*/

        // remake button
        remakeButton = new JButton("Remake");
        //remakeButton.setBounds(1110, 250, 120, 30);
        remakeButton.setBounds(50, 300, 120, 30);
        utilityPanel.add(remakeButton);

        undoButton = new JButton("Undo");
        //remakeButton.setBounds(1110, 250, 120, 30);
        undoButton.setBounds(50, 360, 120, 30);
        utilityPanel.add(undoButton);

        soundButton = new JButton("Set Sound");
        //remakeButton.setBounds(1110, 250, 120, 30);
        soundButton.setBounds(50, 420, 120, 30);
        utilityPanel.add(soundButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(50, 480, 120, 30);
        utilityPanel.add(saveButton);

        quitButton = new JButton("Quit");
        //remakeButton.setBounds(1110, 250, 120, 30);
        quitButton.setBounds(50, 540, 120, 30);
        utilityPanel.add(quitButton);

    }

    public void initializePRButton() {
        pause_resumeButton.setText("Pause");
    }

    public void setPRButton() {
        if (pause_resumeButton.getText().equals("Pause")) {
            pause_resumeButton.setText("Resume");
        } else {
            pause_resumeButton.setText("Pause");
        }
    }

    public JButton getPause_resumeButton() {
        return pause_resumeButton;
    }

    public String getMode() {
        return pause_resumeButton.getText();
    }

    public JPanel getUtilityPanel() {
        return utilityPanel;
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JButton getRemakeButton() {
        return remakeButton;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getSoundButton() {
        return soundButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    @Override
    public void update(Observable o, Object arg) {

        String command = ((String) arg);

        if (command.equals("ChangeScreenPlay")) {

            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            refreshGame();
            this.setVisible(true);

        } else if (command.equals("ResetGame")) {

            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            refreshGame();

        } else if (command.equals("ChangeScreenRemake")) {

            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            refreshGame();

        } else if (command.equals("MovePlayer")) {

            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos() - 1, mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos() + 1, mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos() - 1);
            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos() + 1);

        } else if (command.equals("UpdateTimer")) {
            mg.setMode(getMode());
            if (getMode().equals("Resume")) {
                gamePanel.setVisible(false);
            } else {
                gamePanel.setFocusable(true);
                gamePanel.requestFocusInWindow();
                gamePanel.setVisible(true);
            }
            timerLabel.setText(mg.getCurrTime());

        } else if (command.equals("UndoMove")) {

            gamePanel.setFocusable(true);
            gamePanel.requestFocusInWindow();
            refreshGame();

        } else {

            this.setVisible(false);

        }

    }

    private void refreshGame() {

        for (int y = 0; y < mg.getYSizeOfBoard(); y++) {
            for (int x = 0; x < mg.getXSizeOfBoard(); x++) {
                setPanelAppearance(gameGrid[y][x], x, y);
                gameGrid[y][x].repaint();
                gameGrid[y][x].revalidate();
            }
        }
    }

    private void updateGrid(int x, int y) {

        setPanelAppearance(gameGrid[y][x], x, y);
        gameGrid[y][x].repaint();
        gameGrid[y][x].revalidate();

    }

}

