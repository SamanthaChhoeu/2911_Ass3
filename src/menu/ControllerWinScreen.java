package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ModelInterface;

public class ControllerWinScreen {
    
    private ModelInterface mi;
    private ViewWinScreen vws;
    private ActionListener backToMenu;

    public ControllerWinScreen(ModelInterface mi, ViewWinScreen vws) {
        this.mi = mi;
        this.vws = vws;
        
        // creates the action when the play button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the play button so that the action is performed when the play button is pressed
        vws.getBackToMainMenuButton().addActionListener(backToMenu);
        
    }

    
    
}
