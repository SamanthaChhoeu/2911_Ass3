package main;


import javax.swing.SwingUtilities;

import game.ControllerGame;
import game.ModelGame;
import game.ViewGame;
import menu.ControllerMenu;
import menu.ControllerSettings;
import menu.ControllerWinScreen;
import menu.ControllerGuide;
import menu.ControllerLoadings;
import menu.ControllerSavings;
import menu.ModelInterface;
import menu.ViewMenu;
import menu.ViewSettings;
import menu.ViewWinScreen;
import menu.ViewGuide;
import menu.ViewLoadings;
import menu.ViewSavings;

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
        // vg handles what the user sees in the game
        ViewGame vg = new ViewGame(mi, mg);
        mi.addObserver(vg);
        mg.addObserver(vg);
        // vws handles what the user sees in the win screen
        ViewWinScreen vws = new ViewWinScreen(mi);
        mi.addObserver(vws);
        mg.addObserver(vws);
        
        ViewLoadings vl = new ViewLoadings(mi);
        mi.addObserver(vl);
        ViewSavings vsv = new ViewSavings(mi);
        mi.addObserver(vsv);
		
        ViewGuide vvg = new ViewGuide(mi);
        mi.addObserver(vvg);
      
        // cm controls what happens to the model when a user interacts with the interface
        ControllerMenu cm = new ControllerMenu(mi, mg, vm);
        ControllerSettings cs = new ControllerSettings(mi, vs);
        ControllerGame cg = new ControllerGame(mi, mg, vg);
        ControllerWinScreen cws = new ControllerWinScreen(mi, vws);
        ControllerGuide ccg = new ControllerGuide(mi,vvg);
        ControllerLoadings cl = new ControllerLoadings(mi, mg, vl);
        ControllerSavings csv = new ControllerSavings(mi, mg, vsv);
        
        cm.setupController();
        cs.setupController();
        cg.setupController();
        cws.setupController();
        ccg.setupController();
        cl.setupController();
        csv.setupController();
	}

}
