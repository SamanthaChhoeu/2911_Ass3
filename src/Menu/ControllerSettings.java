package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Main.ModelInterface;

public class ControllerSettings {

    private ModelInterface mi;
    private ViewSettings vs;
    private ActionListener backToMenu;
    
    public ControllerSettings(ModelInterface mi, ViewSettings vs) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vs = vs;
        
        // creates the action when the play button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the play button so that the action is performed when the play button is pressed
        vs.getBackButton().addActionListener(backToMenu);
        
    }

}
