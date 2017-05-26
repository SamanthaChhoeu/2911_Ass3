package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import game.ModelGame;

public class ControllerSavings {

    private ModelInterface mi;
    private ViewSavings vsv;
    private ModelGame mg;
    private ActionListener back;
    private ActionListener one, two, three, four, five;
    private ActionListener backToMenu;

    
    public ControllerSavings(ModelInterface mi, ModelGame mg, ViewSavings vsv) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vsv = vsv;
        // reference to the game model to reset the game every time play is clicked on
        this.mg = mg;

    }
    
    public void setupController() {
        // creates the action when the play button is pressed

        
        // creates the action when the back to menu button is pressed
        backToMenu = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vsv.getQuitButton().addActionListener(backToMenu);
        
        one = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		
        	}
        };
        vsv.getOne().addActionListener(one);
        
        two = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		
        	}
        };
        vsv.getTwo().addActionListener(two);
        
        three = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		
        	}
        };
        vsv.getThree().addActionListener(three);
        
        four = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		
        	}
        };
        vsv.getFour().addActionListener(four);
        
        five = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
        		
        	}
        };
        vsv.getFive().addActionListener(five);
    
    }
    
}
