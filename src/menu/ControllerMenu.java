package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.ModelGame;
import game.ViewGame;

public class ControllerMenu {

    private ModelInterface mi;
    private ViewMenu vm;
    private ModelGame mg;
    private ActionListener playGame;
    private ActionListener goToSettings;
    private ActionListener quit;
    
    public ControllerMenu(ViewGame vg, ModelInterface mi, ModelGame mg, ViewMenu vm) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vm = vm;
        // reference to the game model to reset the game every time play is clicked on
        this.mg = mg;
        
    }
    
    public void setupController() {
        // creates the action when the play button is pressed
        playGame = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mg.generateBoard();
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
            }
        };
        // adds a listener to the play button so that the action is performed when the play button is pressed
        vm.getPlayButton().addActionListener(playGame);
        
        // creates the action when the settings button is pressed
        goToSettings = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mi.setCurrScreen("Settings");
            }
        };
        vm.getSettingsButton().addActionListener(goToSettings);
        
        // creates the action when the quit button is pressed
        quit = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };
        vm.getQuitButton().addActionListener(quit);
    
    }
    
}
