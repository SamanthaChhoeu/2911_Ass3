package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControllerGuide {
	 private ModelInterface mi;
	 private ViewGuide vvg;
	 private ActionListener back;
	 
	  public ControllerGuide(ModelInterface mi, ViewGuide vs) {
	        
	        // reference the model to allow the controller to alter settings
	        this.mi = mi;
	        // reference the view to allow the controller to change things in the view
	        this.vvg = vs;
	        
	  }
	  
	

	  
	  public void setupController () {
	        // creates the action when the back to menu button is pressed
		  
	        back = new ActionListener() {
	            public void actionPerformed(ActionEvent event) {
	                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
	                mi.setCurrScreen("Menu");
	            }
	        };
	        // adds a listener to the menu button so that the action is performed when the menu button is pressed
	        vvg.getBackButton().addActionListener(back);
	  }

}
