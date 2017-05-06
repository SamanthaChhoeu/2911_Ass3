package Main;


import javax.swing.SwingUtilities;

import Game.ControllerGame;
import Game.ModelGame;
import Game.ViewGame;
import Menu.ControllerMenu;
import Menu.ControllerSettings;
import Menu.ViewMenu;
import Menu.ViewSettings;

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
	    // This website helps you understand the ideology behind the mvc pattern
	    // mi stores and processes information required for all interfaces (screen size, curr screen, mute sounds)
        ModelInterface mi = new ModelInterface();
        // vm handles what the user sees in the main menu (buttons, background)
        ViewMenu vm = new ViewMenu(mi);
        // set up so that the main menu view changes when the interface model changes
        mi.addObserver(vm);
        // cm controls what happens to the model when a user interacts with the controller
        ControllerMenu cm = new ControllerMenu(mi, vm);
        
        ViewSettings vs = new ViewSettings(mi);
        mi.addObserver(vs);
        ControllerSettings cs = new ControllerSettings(mi, vs);
        
        //ModelGame mg = new ModelGame();
        //ViewGame vg = new ViewGame(mg);
        //mm.addObserver(vg);
        //ControllerGame gm = new ControllerGame(mg, vg);
        
        
        
	}

}
