package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

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
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
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
        
        //gamePanel.add(gameGrid);
        
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
    
    private void setupUtilityPanel() {
        
        // TODO @Nors @Sharon fix this so that the JPanel shows on the right hand side
        utilityPanel = new JPanel();
        utilityPanel.setBackground(Color.BLACK);
        utilityPanel.setLayout(null);
        utilityPanel.setBounds(1080, 0, 200, 720);
        //utilityPanel.setPreferredSize(new Dimension(200, 720));
        
        // TODO @Nors @Sharon add a timer onto this panel which shows how long a user is taking on a puzzle
        
        // TODO @Nors @Sharon add a pause button which pauses the game and allows the user to resume, restart or quit
        
        utilityPanel.setVisible(true);
        
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
            
            //gamePanel.removeAll();
            initialiseGame();
            this.setVisible(true);
            
        } else if (command.equals("MovePlayer")) {
            
            // TODO @Nors @Sharon fix this so that you don't have to destroy and recreate the gamePanel
            gamePanel.removeAll();
            setupGamePanel();
            //gameGrid[mg.getPlayerYPos()][mg.getPlayerXPos()].revalidate();
            //gameGrid[mg.getPlayerYPos()][mg.getPlayerXPos()].repaint();
            gamePanel.revalidate();
            //gamePanel.repaint();
            
        } else {
            
            this.setVisible(false);
            
        }
        
    }

}
