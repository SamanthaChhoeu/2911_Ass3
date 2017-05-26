package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import game.ModelGame;

public class ControllerLoadings {

    private ModelInterface mi;
    private ViewLoadings vl;
    private ModelGame mg;
    private ActionListener back;
    private ActionListener one, two, three, four, five;
    private ActionListener backToMenu;

    
    public ControllerLoadings(ModelInterface mi, ModelGame mg, ViewLoadings vl) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vl = vl;
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
        vl.getQuitButton().addActionListener(backToMenu);
        
        one = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
                mg.openBoard(1);
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
        	}
        };
        vl.getOne().addActionListener(one);
        
        two = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
                mg.openBoard(2);
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
        	}
        };
        vl.getTwo().addActionListener(two);
        
        three = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
                mg.openBoard(3);
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
        	}
        };
        vl.getThree().addActionListener(three);
        
        four = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
                mg.openBoard(4);
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
        	}
        };
        vl.getFour().addActionListener(four);
        
        five = new ActionListener() {
        	public void actionPerformed(ActionEvent event){
                mg.openBoard(5);
                mg.startTimer();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Play");
        	}
        };
        vl.getFive().addActionListener(five);
    
    }
    
}
