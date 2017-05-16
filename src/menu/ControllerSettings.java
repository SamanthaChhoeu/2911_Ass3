package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.midi.*;

public class ControllerSettings {

    private ModelInterface mi;
    private ViewSettings vs;
    private ActionListener backToMenu;
    private ActionListener musicSwitch;
    private ActionListener music01;
    private ActionListener music02;
     private static Sequencer midiPlayer;
    boolean play = true;
    boolean stop = false;
    
    public ControllerSettings(ModelInterface mi, ViewSettings vs) {
        
        // reference the model to allow the controller to alter settings
        this.mi = mi;
        // reference the view to allow the controller to change things in the view
        this.vs = vs;
        
    }
    
    public void setupController () {
        //Sound("filename.mid");
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
                    if(play){
            			musicstop();
            			play = false;
            			stop = true;
            		}
            	}else if(vs.getbtnSwitch().getText().equals("Off")){
            		vs.getbtnSwitch().setText("On");
                    if(stop){
            			Sound("music02.mid");
            			play = true;
            			stop = false;
            		}
            	}  
            }
        };
        vs.getbtnSwitch().addActionListener(musicSwitch);
        
       
        
        music01 = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	//music1
                if(!play && stop && vs.getbtnSwitch().getText().equals("On")){
                // Sound("filename.mid");
                 play = true;
                 stop = false;
                }
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
     public static boolean Sound(String midFilename) {
    	boolean play = false;
        try {
           File midiFile = new File(midFilename);
           Sequence song = MidiSystem.getSequence(midiFile);
           midiPlayer = MidiSystem.getSequencer();
           midiPlayer.open();
           midiPlayer.setSequence(song);
           midiPlayer.setLoopCount(0); // repeat 0 times (play once)
           midiPlayer.start();
           play = true;
        } catch (MidiUnavailableException e) {
           e.printStackTrace();
        } catch (InvalidMidiDataException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
       return play;
     }
     boolean musicstop(){
       boolean stop = false;
       midiPlayer.stop();
       midiPlayer.close();
       stop = true;
       return stop;
    }

}
