package Menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.ModelFrame;

public class ViewMenu implements Observer {
    
    private ModelFrame mf;
    private JFrame frame;
    private JPanel mainMenuPanel;
    private JButton playButton;
    private JButton settingsButton;
    private JButton quitButton;

    public ViewMenu (ModelFrame mf) {
        
        this.mf = mf;
        createMenu();
        
    }
    
    private void createMenu() {
        
        frame = new JFrame();
        frame.setSize(mf.getWidth(), mf.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        //mainMenuPanel = new JPanel();
        //mainMenuPanel.setLayout(null);
        //frame.add(mainMenuPanel);

        playButton = new JButton("Play");
        playButton.setBounds(390, 375, 500, 50);
        //mainMenuPanel.add(playButton);
        frame.add(playButton);
        
        settingsButton = new JButton("Settings");
        settingsButton.setBounds(390, 450, 500, 50);
        //mainMenuPanel.add(settingsButton);
        frame.add(settingsButton);
        
        quitButton = new JButton("Quit");
        quitButton.setBounds(390, 525, 500, 50);
        //mainMenuPanel.add(quitButton);
        frame.add(quitButton);
        
    }
    
    public JButton getPlayButton() {
        return playButton;
    }
    
    public JButton getSettingsButton() {
        return settingsButton;
    }
    
    public JButton getQuitButton() {
        return quitButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        ModelFrame mm = ((ModelFrame) o);
        
        if (mm.getCurrScreen() == "Play") {
            
            frame.dispose();
            
        }
        
    }
    
}
