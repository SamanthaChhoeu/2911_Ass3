package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

import menu.ModelInterface;

public class ControllerGame {

    private ModelGame mg;
    private ViewGame vg;
    private ModelInterface mi;
    private KeyListener playerControls;
    private ActionListener remake;
    private ActionListener undo;
    private ActionListener triggerSound;
    private ActionListener quit;  
    private ActionListener save;
  
    private ActionListener pause_resume;
    private long pauseStart;
    
    public ControllerGame(ModelInterface mi, ModelGame mg, ViewGame vg) {

        this.mg = mg;
        this.vg = vg;
        this.mi = mi;
        
    }

    public void setupController () {
        
        playerControls = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
                if(KeyEvent.getKeyText(e.getKeyCode()).equals("Left")) {
                    mg.movePlayerLeft();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")) {
                    mg.movePlayerRight();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Up")) {
                    mg.movePlayerUp();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Down")) {
                    mg.movePlayerDown();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) {
                    mg.resetGame();
                } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("U")) {
                    mg.undoMove();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        };
        
        vg.getGamePanel().addKeyListener(playerControls);
        
        remake = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mg.generateBoard();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Remake");
            }
        };
        vg.getRemakeButton().addActionListener(remake);
        
        undo = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mg.undoMove();
            }
        };
        vg.getUndoButton().addActionListener(undo);
        
        triggerSound = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
            }
        };
        vg.getSoundButton().addActionListener(triggerSound);
        
         save = new ActionListener(){
        	public void actionPerformed(ActionEvent event) {
        		Object[] options ={ "Save", "Cancel" };
        		JOptionPane.showInputDialog(null,"Enter your Username: ","Save",JOptionPane.PLAIN_MESSAGE,null,null,null);  
            }
        	
        };
        vg.getSaveButton().addActionListener(save);
        
        quit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
                mg.cancelTimer();
                vg.initializePRButton();
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
        vg.getQuitButton().addActionListener(quit);
        
        pause_resume = new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		vg.setPRButton();
        	}
        };
        vg.getPause_resumeButton().addActionListener(pause_resume);
    }
    
    public long getPauseStart() {
    	return pauseStart;
    }

}
