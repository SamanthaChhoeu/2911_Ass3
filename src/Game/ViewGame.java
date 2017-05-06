package Game;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.ModelFrame;

public class ViewGame implements Observer {

    private ModelFrame mf;
    private ModelGame mg;
    private JFrame frame;
    private JPanel gamePanel;
    
    public ViewGame(ModelFrame mf, ModelGame mg) {
        
        this.mf = mf;
        this.mg = mg;
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
        ModelFrame mm = ((ModelFrame) o);
        
        if (mm.getCurrScreen().equals("Play")) {
            
            initialiseGame();
        
        }
        
    }

    private void initialiseGame() {

        frame = new JFrame();
        frame.setSize(mf.getWidth(), mf.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        gamePanel = new JPanel();
        gamePanel.setLayout(null);
        frame.add(gamePanel);
        
    }

}
