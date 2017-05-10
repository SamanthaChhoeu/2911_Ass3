package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerWinScreen {
    
    private ModelInterface mi;
    private ViewWinScreen vws;
    private ActionListener backToMenu;

    public ControllerWinScreen(ModelInterface mi, ViewWinScreen vws) {
        
        this.mi = mi;
        this.vws = vws;
        
    }

    public void setupController() {
        
        // creates the action when the menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vws.getBackToMainMenuButton().addActionListener(backToMenu);
        
    }
    
}
