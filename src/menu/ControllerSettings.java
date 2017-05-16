package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSettings {

    private ModelInterface mi;
    private ViewSettings vs;
    private ActionListener backToMenu;
    private ActionListener musicSwitch；
    private ActionListener music01;
    private ActionListener music02;
    
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
        
        musicSwitch = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            //performance after music on
             if(vs.getbtnSwitch().getText().equals("On")){
            		vs.getbtnSwitch().setText("Off");
            	}else if(vs.getbtnSwitch().getText().equals("Off")){
            		vs.getbtnSwitch().setText("On");
            	}  
            }
        };
        vs.getbtnSwitch().addActionListener(musicSwitch);
        
       
        
        music01 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	//music1
                
            }
        };
        vs.getbtnMusic1().addActionListener(music01);
        
        music02 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            //music2	
                
            }
        };
        vs.getbtnMusic2().addActionListener(music02);
        
    }

}
