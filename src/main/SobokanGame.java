package main;


import javax.swing.SwingUtilities;

import game.ControllerGame;
import game.ModelGame;
import game.ViewGame;
import menu.ControllerMenu;
import menu.ControllerSettings;
import menu.ViewMenu;
import menu.ViewSettings;

public class SobokanGame {
    
	public static void main(String[] args) {
	    
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            
	            startGame();
	            
	        }
	    });
	
	}
	
	private static void startGame() {
	    
	    // http://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
	    // This link makes it easy to understand how mvc pattern works
	    
	    // mi stores and processes information required for all interfaces (screen size, curr screen, mute sounds)
        ModelInterface mi = new ModelInterface();
        // mg stores and processes information for the game (store & generate maze, timer)
        ModelGame mg = new ModelGame();
        
        // vm handles what the user sees in the main menu (buttons, background)
        ViewMenu vm = new ViewMenu(mi);
        // set up so that the main menu view changes when the interface model changes
        mi.addObserver(vm);
        // vs handles what the user sees in the settings menu
        ViewSettings vs = new ViewSettings(mi);
        mi.addObserver(vs);
        // vg handlse what the user sees in the game
        ViewGame vg = new ViewGame(mi, mg);
        mi.addObserver(vg);
        mg.addObserver(vg);
        
        // cm controls what happens to the model when a user interacts with the interface
        ControllerMenu cm = new ControllerMenu(mi, vm);
        ControllerSettings cs = new ControllerSettings(mi, vs);
        ControllerGame gm = new ControllerGame(mg, vg);
        
        
        
	}

}
