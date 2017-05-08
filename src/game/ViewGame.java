package game;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ModelInterface;

public class ViewGame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private ModelGame mg;
    private JPanel gamePanel;
    private JPanel utilityPanel;
    private JButton backButton;
    
    public ViewGame(ModelInterface mi, ModelGame mg) {
        
        this.mi = mi;
        this.mg = mg;
        
        this.setSize(mi.getDimensions());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        //this.setLayout(new FlowLayout());
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
        for (int i = 0; i < mg.getXSizeOfBoard()*mg.getYSizeOfBoard(); i++) {
            JPanel tilePanel = new JPanel();
            setPanelAppearance(i, tilePanel);
            gamePanel.add(tilePanel);
        }
        gamePanel.setBounds(0, 0, 72 * mg.getXSizeOfBoard(), 72 * mg.getYSizeOfBoard());
        //gamePanel.setSize(72 * mg.getXSizeOfBoard(), 72 * mg.getYSizeOfBoard());
        this.add(gamePanel);
        
    }

    private void setPanelAppearance(int i, JPanel tilePanel) {
        
        if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "bg") {
            tilePanel.setBackground(Color.GREEN);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "pg") {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "w") {
            tilePanel.setBackground(Color.BLACK);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "p") {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "b") {
            tilePanel.setBackground(Color.YELLOW);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "g") {
            tilePanel.setBackground(Color.BLUE);
        } else {
            tilePanel.setBackground(Color.WHITE);
        }
        
    }
    
    private void setupUtilityPanel() {
        
        utilityPanel = new JPanel();
        utilityPanel.setLayout(null);
        //utilityPanel.setBounds(1080, 0, 200, 720);
        utilityPanel.setBounds(0, 0, 200, 720);
        
        backButton = new JButton("Back");
        backButton.setBounds(10, 10, 10, 10);
        utilityPanel.add(backButton);
        
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
            
            initialiseGame();
            this.setVisible(true);
            
        } else if (command.equals("MovePlayer")) {
            
            gamePanel.removeAll();
            setupGamePanel();
            gamePanel.revalidate();
            gamePanel.repaint();
            
        } else {
            
            this.setVisible(false);
            
        }
        
    }

}
