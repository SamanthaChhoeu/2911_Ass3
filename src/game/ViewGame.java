package game;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.ModelInterface;

public class ViewGame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private ModelGame mg;
    private JPanel gamePanel;
    
    public ViewGame(ModelInterface mi, ModelGame mg) {
        
        this.mi = mi;
        this.mg = mg;
        initialiseGame();
        
    }
    
    private void initialiseGame() {

        this.setSize(mi.getDimensions());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(mg.getXSizeOfBoard(), mg.getYSizeOfBoard()));
        for (int i = 0; i < mg.getXSizeOfBoard()*mg.getYSizeOfBoard(); i++) {
            JPanel tilePanel = new JPanel();
            setPanelAppearance(i, tilePanel);
            gamePanel.add(tilePanel);
        }
        gamePanel.setSize(720,720);
        this.add(gamePanel);
        
    }

    private void setPanelAppearance(int i, JPanel tilePanel) {
        
        if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "w") {
            tilePanel.setBackground(Color.BLACK);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "p") {
            tilePanel.setBackground(Color.RED);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "b") {
            tilePanel.setBackground(Color.DARK_GRAY);
        } else if (mg.getSobokanBoardAtXY(i%mg.getXSizeOfBoard(), i/mg.getXSizeOfBoard()) == "g") {
            tilePanel.setBackground(Color.GREEN);
        } else {
            tilePanel.setBackground(Color.WHITE);
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
        ModelInterface mm = ((ModelInterface) o);
        
        if (mm.getCurrScreen().equals("Play")) {
            
            //initialiseGame();
            this.setVisible(true);
        
        } else {
            
            this.setVisible(false);
            
        }
        
    }

}
