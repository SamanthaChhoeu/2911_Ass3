package menu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ViewWinScreen extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JButton backToMainMenuButton;
    private PicturePanel mainPanel;

    public ViewWinScreen(ModelInterface mi) {
        
        // the model interface is referenced to allow to view to get details from the model
        this.mi = mi;
        setupWinScreen();
        
    }
    
    private void setupWinScreen() {
        
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        
        // create a panel on top of the frame which holds all the UI elements
        mainPanel = new PicturePanel();
        // manually place all the elements
        mainPanel.setLayout(null);
        //mainPanel.setVisible(true);
        // sets the panel to a colour if picture not working TODO delete this once complete
        mainPanel.setBackground(Color.DARK_GRAY);
        // add this panel to the frame
        this.add(mainPanel);
        
        // creates a new button to quit the game
        backToMainMenuButton = new JButton("Back to Main Menu");
        backToMainMenuButton.setBounds(390, 525, 500, 50);
        this.add(backToMainMenuButton);
        
        
    }

    public JButton getBackToMainMenuButton() {
        return backToMainMenuButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        String command = ((String) arg);
        
        if (command.equals("ChangeScreenWin")) {
            
            this.setVisible(true);
            
        } else {
            
            this.setVisible(false);
            
        }
    }

}
