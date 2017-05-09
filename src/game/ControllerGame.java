package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerGame {

    private ModelGame mg;
    private ViewGame vg;
    private KeyListener playerControls;
    
    public ControllerGame(ModelGame mg, ViewGame vg) {
        
        this.mg = mg;
        this.vg = vg;
        
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
                } /*else if (KeyEvent.getKeyText(e.getKeyCode()).equals("R")) {
                    mg.resetGame();
                } */
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        };
        
        vg.getGamePanel().addKeyListener(playerControls);
    }

}
