package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import menu.ModelInterface;

public class ViewGame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private ModelGame mg;
    private JPanel gamePanel;
    private JPanel[][] gameGrid;
    private JPanel utilityPanel;
    private JButton backButton;
    private JLabel timerLabel;
    
    public ViewGame(ModelInterface mi, ModelGame mg) {
        
        this.mi = mi;
        this.mg = mg;
        
        initialiseFrame();
        initialiseGame();
        
    }
    
    private void initialiseFrame() {
        
        this.setSize(mi.getDimensions());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
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
        
        gamePanel.setBounds(0, 0, 1080, 720);
        //gamePanel.setPreferredSize(new Dimension(1080, 720));
        
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        
        this.add(gamePanel);
        
    }
    
   private void setPanelAppearance(JPanel tilePanel, int i, int j) {
        
        if (mg.getSobokanBoardAtXY(i,j) == "bg") {
            tilePanel.setBackground(Color.GREEN);
        } else if (mg.getSobokanBoardAtXY(i,j) == "pg") {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i,j) == "w") {
            tilePanel.setBackground(Color.BLACK);
        } else if (mg.getSobokanBoardAtXY(i,j) == "p") {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i,j) == "b") {
            tilePanel.setBackground(Color.YELLOW);
        } else if (mg.getSobokanBoardAtXY(i,j) == "g") {
            tilePanel.setBackground(Color.BLUE);
        } else {
            tilePanel.setBackground(Color.WHITE);
        }
        
   }
    
   //Function below is timer part, it can run, but not perfectly supports button operations.
   //Let me know if someone else needs more features to it :)
   public void startCounting(){
        
    }
    
    
    private void setupUtilityPanel() {

        utilityPanel = new JPanel();
        utilityPanel.setBackground(Color.BLACK);
        utilityPanel.setLayout(null);
        utilityPanel.setBounds(1080, 0, 200, 720);
        //utilityPanel.setPreferredSize(new Dimension(200, 720));
        
        timerLabel=new JLabel("",JLabel.CENTER);
        timerLabel.setBounds(1110, 150, 120, 30);
        timerLabel.setFont(new Font("Default", Font.PLAIN, 30));
        this.add(timerLabel);
        
        backButton = new JButton();
        
        //utilityPanel.setVisible(true);
        
    }
    
    public JPanel getGamePanel() {
        return gamePanel;
    }
    
    public JButton getBackButton() {
        return backButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        String command = ((String) arg);
        
        if (command.equals("ChangeScreenPlay")) {

            refreshGame();
            this.setVisible(true);
            
        } else if (command.equals("MovePlayer")) {
            
            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos() - 1, mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos() + 1, mg.getPlayerYPos());
            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos() - 1);
            updateGrid(mg.getPlayerXPos(), mg.getPlayerYPos() + 1);
            
        } else if (command.equals("UpdateTimer")) {
        
            System.out.println(mg.getCurrTime());
            timerLabel.setText(mg.getCurrTime());
            
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
