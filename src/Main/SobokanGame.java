package Main;


import javax.swing.SwingUtilities;

import Game.ControllerGame;
import Game.ModelGame;
import Game.ViewGame;
import Menu.ControllerMenu;
import Menu.ViewMenu;

public class SobokanGame {
    
	public static void main(String[] args) {
	    
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            
	            startGame();
	            
	        }
	    });
	
	}
	
	private static void startGame() {
	    
        ModelFrame mm = new ModelFrame();
        ViewMenu vm = new ViewMenu(mm);
        mm.addObserver(vm);
        ControllerMenu cm = new ControllerMenu(mm, vm);
        
        ModelGame mg = new ModelGame();
        ViewGame vg = new ViewGame(mm, mg);
        mm.addObserver(vg);
        ControllerGame gm = new ControllerGame(mg, vg);
        
	}

}
