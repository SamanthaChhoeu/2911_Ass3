package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSettings {

    private ModelInterface mi;
    private ViewSettings vs;
    private ActionListener backToMenu;
    
    public ControllerSettings(ModelInterface mi, ViewSettings vs) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vs = vs;
        
    }
    
    public void setupController () {
        // creates the action when the back to menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vs.getBackButton().addActionListener(backToMenu);
    }

}
