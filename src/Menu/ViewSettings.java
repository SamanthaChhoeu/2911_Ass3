package Menu;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

import Main.ModelInterface;

public class ViewSettings extends JFrame implements Observer {

    private static final long serialVersionUID = -3451639509158630962L;
    private ModelInterface mi;
    private JButton backButton;

    public ViewSettings(ModelInterface mi) {
        
        this.mi = mi;
        
        // sets the size of the window
        this.setSize(mi.getDimensions());
        // sets what happens when the user closes the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the layout to a manual layout to enable full customisation of where the buttons are
        this.setLayout(null);
        // sets the window to show in the middle of the screen
        this.setLocationRelativeTo(null);
        
        // creates a new button to go back to the main menu
        backButton = new JButton("Back");
        backButton.setBounds(1025, 600, 200, 50);
        this.add(backButton);
    
    }
    
    public JButton getBackButton() {
        return backButton;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        ModelInterface mi = ((ModelInterface) o);
        
        // checks to see whether you should show the settings menu
        if (mi.getCurrScreen() == "Settings") {
            
            this.setVisible(true);
            
        } else {
            
            this.setVisible(false);
            
        }
        
    }

}
