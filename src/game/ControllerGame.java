package game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import menu.ModelInterface;

import menu.ModelInterface;

public class ControllerGame implements Observer {

    private ModelGame mg;
    private ViewGame vg;
    private ModelInterface mi;
    private KeyListener playerControls;
    private ActionListener backToMenu;
    private Timer gameTimer;
    private ActionListener remake; 
    
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
        /*
        backToMenu = new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent event) {
                // when the menu button is pressed, sets the current screen being viewed to the main menu screen
                mi.setCurrScreen("Menu");
            }
        };
        // adds a listener to the menu button so that the action is performed when the menu button is pressed
*/
        
        
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            public void run() {
                mg.updateTimer();
            }
        },0,1000);//refresh every second with no delay.
        
        remake = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mg.generateBoard();
                // when the play button is pressed, sets the current screen being viewed to the game screen
                mi.setCurrScreen("Remake");
            }
        };
        vg.getRemakeButton().addActionListener(remake);
        
    }

    @Override
    public void update(Observable o, Object arg) {
       
        String command = ((String) arg);
        
        if (command.equals("ChangeScreenWin")) {

            gameTimer.cancel();
            
        }
        
    }

}
