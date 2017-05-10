package menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewMenu extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JButton playButton;
    private JButton settingsButton;
    private JButton quitButton;

    public ViewMenu (ModelInterface mi) {   
        
        // the model interface is referenced to allow to view to get details from the model
        this.mi = mi;
        setupMenu();
        
    }
    
    private void setupMenu() {

        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);

        // creates a new button to start playing the game
        playButton = new JButton("Play");
        // sets the size and the position of the buttons (only works for null layout)
        playButton.setBounds(390, 375, 500, 50);
        // adds the play button to the current frame
        this.add(playButton);
        
        // creates a new button to access the settings of the game
        settingsButton = new JButton("Settings");
        settingsButton.setBounds(390, 450, 500, 50);
        this.add(settingsButton);
        
        // creates a new button to quit the game
        quitButton = new JButton("Quit");
        quitButton.setBounds(390, 525, 500, 50);
        this.add(quitButton);
        
        // set such that the main menu is the visible when this class is created
        this.setVisible(true);
        
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
        
        String command = ((String) arg);
        
        // check whether or not to show this screen
        if (command.equals("ChangeScreenMenu")) {
                
            this.setVisible(true);

        } else {
            
            this.setVisible(false);
            
        }
        
    }
    
}
