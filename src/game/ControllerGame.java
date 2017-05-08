package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerGame {

    private ModelGame mg;
    private ViewGame vg;
    private KeyListener playerControls;
    
    public ControllerGame(ModelGame mg, ViewGame vg) {
<<<<<<< HEAD
        
=======
        setMg(mg);
        setVg(vg);
    }

    public void setMg(ModelGame mg){
>>>>>>> 3b4afc81cbc7b1928eb75033838747a57e202241
        this.mg = mg;
    }

    public void setVg(ViewGame vg){
        this.vg = vg;
        
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
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
            
        };
        
        vg.getGamePanel().setFocusable(true);
        vg.getGamePanel().requestFocusInWindow();
        vg.getGamePanel().addKeyListener(playerControls);
        
    }

    public ModelGame getMg(){
        return this.mg;
    }

    public ViewGame getVg(){
        return this.vg;
    }

}
