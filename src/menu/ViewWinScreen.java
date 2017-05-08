package menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.ModelInterface;

public class ViewWinScreen extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;
    private ModelInterface mi;
    private JButton backToMainMenuButton;

    public ViewWinScreen(ModelInterface mi) {
        // the model interface is referenced to allow to view to get details from the model
        this.mi = mi;
        
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        
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
