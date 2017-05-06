package Game;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.ModelInterface;

public class ViewGame implements Observer {

    private ModelInterface mi;
    private ModelGame mg;
    private JFrame frame;
    private JPanel gamePanel;
    
    public ViewGame(ModelInterface mi, ModelGame mg) {
        
        this.mi = mi;
        this.mg = mg;
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
        ModelInterface mm = ((ModelInterface) o);
        
        if (mm.getCurrScreen().equals("Play")) {
            
            initialiseGame();
        
        }
        
    }

    private void initialiseGame() {

        frame = new JFrame();
        frame.setSize(mi.getDimensions());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        frame.add(gamePanel);
        
    }

}
